#!/bin/bash
BASH_SOURCE="-bash"

SOURCE_FILE='../scripts/setenv'
if [ -f $SOURCE_FILE ]; then
  status_code=true
else
  status_code=false
fi

if $status_code; then
  $SOURCE_FILE $1
else
  echo "ERROR: Not able to access setenv"
fi
