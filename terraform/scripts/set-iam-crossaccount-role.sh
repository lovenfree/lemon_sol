#!/bin/bash


if [[ "$0" == "$BASH_SOURCE" ]]; then
    echo "$0: Please source this file."
    echo "e.g. source set-iam-crossaccount-role.sh"
    exit 1
fi

if [ $# -ne 3 ]; then
    echo "Usage: $0 set-iam-crossaccount-role.sh aws-profile-name aws-role-name-to-assume account_id-to-assume-in"
    return 1
fi

AWS_PROFILE_NAME="$1"
AWS_ROLE_TO_ASSUME="$2"
ACCOUNT_ID="$3"

if [[ "$SHELL" == *"zsh"* ]]; then
  SCRIPT_DIR=$(dirname ${(%):-%N})
else
  SCRIPT_DIR=$(dirname ${BASH_SOURCE[0]})
fi
SCRIPT_DIR=$(cd $SCRIPT_DIR && pwd)

# Check for Windows GIT Bash environment.

ENV_CHECK=$(uname -s)
if [[ "$ENV_CHECK" =~ "MINGW" ]] ; then
  SCRIPT_DIR_MOUNT=$(cygpath -w ${SCRIPT_DIR})
  HOME=$(cygpath -w ${HOME})
  INTERFACE="winpty"
else
  SCRIPT_DIR_MOUNT=$SCRIPT_DIR
  INTERFACE=""
fi

# Call docker.
$INTERFACE docker run -it --rm -v $HOME/.aws:/root/.aws -v "$SCRIPT_DIR_MOUNT":/usr/src/app gunmetalz/aws-iam-python3 python iam/set-iam-crossaccount-role.py -p $AWS_PROFILE_NAME -r $AWS_ROLE_TO_ASSUME -a $ACCOUNT_ID "-e export" \
  && source $SCRIPT_DIR/temp_creds \
  && rm -f $SCRIPT_DIR/temp_creds \
  && printf "Your temporary credentials have been exported as the following environment variables:
AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY, AWS_SESSION_TOKEN and AWS_DEFAULT_REGION and AWS_SESSION_USER_ID \n
Your credentials will expire in 60 minutes
You are now logged in with the following AWS account details:\n" \
  && aws sts get-caller-identity
