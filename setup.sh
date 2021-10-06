#!/bin/bash
BLACK="\033[0;30m"
RED="\033[0;31m"
GREEN="\033[0;32m"
YELLOW="\033[0;33m"
BLUE="\033[0;34m"
PURPLE="\033[0;35m"
CYAN="\033[0;36m"
WHITE="\033[0;37m"
# No Color
NC='\033[0m'

echo -ne "${BLUE}Is this OAuth? ${NC}"
read -p "" -n 1 -r
echo # move to a new line
if [[ $REPLY =~ ^[Yy]$ ]]; then
	echo Removing non-OAuth files
	rm src/main/java/com/usermind/usermindsdk/authentication/credentials/SdktemplateConnectionData.java
	rm src/main/java/com/usermind/usermindsdk/authentication/AuthenticationServiceSdktemplate.java
	mv src/main/java/com/usermind/usermindsdk/authentication/credentials/SdktemplateConnectionDataOauth.java src/main/java/com/usermind/usermindsdk/authentication/credentials/SdktemplateConnectionData.java
  mv src/main/java/com/usermind/usermindsdk/authentication/AuthenticationServiceSdktemplateOauth.java src/main/java/com/usermind/usermindsdk/authentication/AuthenticationServiceSdktemplate.java

else
	echo Removing OAuth files
	rm src/main/java/com/usermind/usermindsdk/authentication/credentials/SdktemplateConnectionDataOauth.java
	rm src/main/java/com/usermind/usermindsdk/authentication/AuthenticationServiceSdktemplateOauth.java


  echo -ne "${BLUE}Are there sessions? ${NC}"
  read -p "" -n 1 -r
  echo # move to a new line
  if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo Removing session management files
    rm src/main/java/com/usermind/usermindsdk/authentication/credentials/SdktemplateSessionManager.java
    rm src/main/java/com/usermind/usermindsdk/authentication/credentials/SdktemplateSession.java
  else
    echo Leaving session management files
  fi
fi
