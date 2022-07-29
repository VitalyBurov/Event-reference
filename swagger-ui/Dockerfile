FROM swaggerapi/swagger-ui:latest
MAINTAINER ITAcademyAfisha

ADD /classifier-service/spec/classifier-service.yml /usr/share/nginx/html/doc/classifier-service.yml
ADD /event-service/spec/event-service.yml /usr/share/nginx/html/doc/event-service.yml
ADD /user-service/spec/user-service.yml /usr/share/nginx/html/doc/user-service.yml

ENV URLS="[{ url: 'doc/classifier-service.yml', name: 'Classifier'},{ url: 'doc/event-service.yml', name: 'Events'},{ url: 'doc/user-service.yml', name: 'User'}]"
ENV URLS_PRIMARY_NAME="Events"