#!/bin/sh
set -e

echo "Waiting for ${NABIDH_ATOMFEED_DB_HOST}.."
sh wait-for.sh -t 300 "${NABIDH_ATOMFEED_DB_HOST}":"${NABIDH_ATOMFEED_DB_PORT}"

echo "Waiting for ${OPENMRS_HOST}.."
sh wait-for.sh -t 3600 "${OPENMRS_HOST}":"${OPENMRS_PORT}"

echo "[INFO] Starting Application"
java -jar $DEBUG_OPTS nabidh-atomfeed.jar --nabidh.atomfeed.db.host=$NABIDH_ATOMFEED_DB_HOST --nabidh.atomfeed.db.port=$NABIDH_ATOMFEED_DB_PORT --nabidh.atomfeed.db.name=$NABIDH_ATOMFEED_DB_NAME --nabidh.atomfeed.db.username=$NABIDH_ATOMFEED_DB_USERNAME --nabidh.atomfeed.db.password=$NABIDH_ATOMFEED_DB_PASSWORD --nabidh.url=$NABIDH_URL --nabidh.username=$NABIDH_USERNAME --nabidh.password=$NABIDH_PASSWORD --openmrs.host=$OPENMRS_HOST --openmrs.port=$OPENMRS_PORT --openmrs.user=$OPENMRS_ATOMFEED_USER --openmrs.password=$OPENMRS_ATOMFEED_PASSWORD
