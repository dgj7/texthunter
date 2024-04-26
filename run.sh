#!/bin/bash

# build the app (maybe)
if [ ! -f th-ui/target/th-ui-0.0.1-jar-with-dependencies.jar ]; then
    ./build-silent.sh
else
    printf "no build needed, skipping.\n"
fi

# run the app
printf "launching..."
(cd th-ui/target ; java -jar th-ui-0.0.1-jar-with-dependencies.jar &)
printf "done.\n"
