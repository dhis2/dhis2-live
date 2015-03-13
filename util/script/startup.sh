#!/bin/sh

# get ready to catch errors
trap ctrl_c INT

ctrl_c () {
    echo
    echo "============================================"
    echo "Keyboard interrupt"
    echo "DHIS2 live exited"
    echo "============================================"
    exit
}

unexpected_exit () {
    echo "============================================"
    echo "DHIS2 live exited with an error"
    echo "Make sure you have a java runtime in your path"
    echo "============================================"
    read -p "Press enter to exit" ch
    exit
}

echo "Starting DHIS2 live ..."

DHIS2LIVE=`dirname $0`
java  -jar $DHIS2LIVE/dhis2-live.jar || unexpected_exit
echo "DHIS2 live exited normally"

