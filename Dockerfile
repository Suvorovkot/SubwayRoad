FROM hseeberger/scala-sbt
RUN mkdir -p /SubwayRoad
WORKDIR /SubwayRoad
COPY . /SubwayRoad
CMD sbt run