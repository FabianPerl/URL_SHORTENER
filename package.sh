#!/usr/bin/env bash

# This script is used to package the relevant contents of this project into a ZIP file. This ZIP file can then be used
# to send it to applicants.

DIR_NAME=${PWD##*/}

rm -f assignment.zip
cd ..
zip -r $DIR_NAME/assignment.zip $DIR_NAME/src $DIR_NAME/README.md $DIR_NAME/pom.xml