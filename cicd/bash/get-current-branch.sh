#!/bin/sh
# This script is executed by other shell scripts. It detects the git branch name from the environment and
# transforms it into a shortened label for the build and deployment process.
#
# This script has been tested with Bitbucket Pipelines and AWS CodeBuild

# The value returned by this script is:
#   branch: the abbreviated, formatted branch name based on the story number, e.g. "abc1234" or "master"

# Format the branch name
if [ -z "$branch" ]; then
    branch=${BRANCH_NAME}
    # Format the branch from "feature/abc-123_my_branch" to "abc123"
    # "The underscores ( _ ) in the feature, bugfix, and hotfix branch names are important to support robust resource naming in AWS for the pipeline.
    # The branch portion of resource names are truncated at the underscore in order to keep the full resource names under the 64-character limit."
    branch=$(echo $branch | cut -d\/ -f2 | cut -d\- -f1-2 | cut -d\_ -f1 | tr '[:upper:]' '[:lower:]' | sed 's/-//g ; s/_//g')
    
    echo -e "\nbranch has been set to '$branch'\n" 1>&2
else
    echo -e "\nbranch is already defined as '$branch'\n" 1>&2
fi

echo $branch