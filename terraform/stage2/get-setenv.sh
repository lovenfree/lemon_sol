#!/bin/bash
BASH_SOURCE="-bash"
# if [[ "$0" == "$BASH_SOURCE" ]]; then
#   echo "$0: Please source this file."
#   echo "e.g. source ./get-setenv.sh configurations/data-rnd-us-vet1-v1"
#   return 1
# fi

SOURCE_FILE='../scripts/setenv'
if [ -f $SOURCE_FILE ]; then
  status_code=true
else
  status_code=false
fi

echo "param1 = $1"

if $status_code; then
  $SOURCE_FILE $1
else
  echo "ERROR: Not able to access setenv"
fi
