FROM alpine

RUN apk --no-cache add curl


ADD event.sh /event.sh

ENTRYPOINT ["/event.sh"]