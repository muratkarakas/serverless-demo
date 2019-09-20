#!/bin/sh

set -ex

while true; do
    curl -v -XPOST  ${SINK} \
  -H "X-B3-Flags: 1" \
  -H "CE-SpecVersion: 0.2" \
  -H "CE-Type: dev.knative.eventing.samples.heartbeat" \
  -H "CE-Time: 2018-04-05T03:56:24Z" \
  -H "CE-ID: 45a8b444-3213-4758-be3f-540bf93f85ff" \
  -H "CE-Source: dev.knative.example" \
  -H 'Content-Type: application/json' \
  -d '{ "much": "wow" }'
    sleep 60
done