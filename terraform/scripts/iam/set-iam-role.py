#!/usr/bin/env python

# Script to assume a role
# "====================================================================="

# ====================================================================="
# To run: python set-iam-role.py -p aws-profile-name -r aws-role-name-to-assume
# Install necessary python packages via pip
#####################################################################

import boto3
import sys
import argparse
import os
from os import environ
from os.path import expanduser
import configparser
import datetime

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('-p', '--profile',
                        help="Name of aws profile in ~/.aws/credentials",
                        required=True)
    parser.add_argument('-r', '--role',
                        help="aws role name to assume",
                        required=True)
    parser.add_argument('-e', '--export',
                        help="export aws keys",
                        required=False)
    arg = parser.parse_args()

   # Default filter if no options are specified
    filter=[]
    profile=""
    role= ""

    outputformat = 'json'
    region = 'ap-northeast-2'

    if arg.profile:
        profile=arg.profile
    if arg.role:
        role=arg.role
        awsprofilename = role + '-saml'

    session = boto3.session.Session(profile_name=profile)

    sts_client = session.client('sts')
    account_id = sts_client.get_caller_identity()["Account"]
    role_arn = 'arn:aws:iam::' + account_id + ':role/' + role
    user_name  = sts_client.get_caller_identity()["Arn"].split('/')[1]

    token = sts_client.assume_role(
        RoleArn=role_arn,
        RoleSessionName=user_name
    )



    credentials = token['Credentials']

    #print credentials
    #print credentials['Expiration']

    if arg.export:
        with open('./temp_creds', 'w') as f:
            f.write('export AWS_ACCESS_KEY_ID=' + credentials['AccessKeyId'] + '\n')
            f.write('export AWS_SECRET_ACCESS_KEY=' + credentials['SecretAccessKey'] + '\n')
            f.write('export AWS_SESSION_TOKEN=' + credentials['SessionToken'] + '\n')
            f.write('export AWS_DEFAULT_REGION=' + region + '\n')
            f.write('export AWS_SESSION_USER_ID=' + user_name + '\n')
            f.write('(sh -c "sleep 3600 && printf \\"\n\n*** AWS Credentials Expired ***\n\n\\""&)\n')
        f.closed
        exit
    else:
        # Write the AWS STS token into the AWS credential file
        if 'AWS_CONFIG_FILE' in os.environ:
            filename = os.environ['AWS_CONFIG_FILE']
        else:
            home = expanduser("~")
            filename = home + '/.aws/credentials'


        # Read in the existing config file
        config = configparser.RawConfigParser()
        config.read(filename)

        # Put the credentials into a specific profile instead of clobbering
        # the default credentials
        if not config.has_section(awsprofilename):
            config.add_section(awsprofilename)

        config.set(awsprofilename, 'output', outputformat)
        config.set(awsprofilename, 'region', region)
        config.set(awsprofilename, 'aws_access_key_id', credentials['AccessKeyId'])
        config.set(awsprofilename, 'aws_secret_access_key', credentials['SecretAccessKey'])
        config.set(awsprofilename, 'aws_session_token', credentials['SessionToken'])
        config.set(awsprofilename, 'creation_utc_time', datetime.datetime.utcnow())
        config.set(awsprofilename, 'expiration_utc_time', credentials['Expiration'])
        config.set(awsprofilename, 'aws_role_arn', role_arn)

        # Write the updated config file
        with open(filename, 'w+') as configfile:
            config.write(configfile)

        # Give the user some basic info as to what has just happened
        print("\n\n----------------------------------------------------------------")
        print("Your new access key pair has been stored in the AWS configuration file {0}".format(filename))
        print("Access keys are stored under {0} profile".format(awsprofilename))
        print("Current date in UTC is {0}".format(datetime.datetime.utcnow()))
        print("Note that it will expire at {0}.".format(credentials['Expiration']))
        print("After this time you may safely rerun this script to refresh your access key pair.")
        print("To use this credential call the AWS CLI with the --profile option (e.g. aws --profile {0} ec2 describe-instances".format(awsprofilename))
        print("----------------------------------------------------------------\n\n")


if __name__ == '__main__':
    sys.exit(main())