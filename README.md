This is a curated version of the project. For a working copy please contact me

![image](https://github.com/user-attachments/assets/3a0ecdca-7e65-4d50-92fb-359ee708c78b)

Video Microservice (VM):

• GET “/”: Returns all the users in the DB.

• POST “/”: Creates a user specified in the body of the request.

• GET “/{userId}”: Returns user info for user with userID (doesn’t include sensitive database specific information,
like the id).

• POST “/{userId}/videos”: Adds a video for the user.

• GET “/{userId}/videos”: Returns the videos posted by the user

• GET “/{userId}/viewed”: Returns the videos viewed by the user. Similarly for /liked and /disliked

• PUT “/{userId}/viewed/{videoId}”: Adds video to set of videos viewed by the user. Same for /liked and /disliked


The HashtagController handles hashtag specific request on the”/hashtags” path:

• GET “/”: Returns all the hashtags in the DB

• GET “/{hashtag}”: Returns hashtag info for hashtag with hashtagId (doesn’t include sensitive database id).

• GET “/hashtagId}/videos": Returns all the videos posted with this hashtag


The VideosController handles video specific requests on the “/videos” path:

• GET “/”: Returns all the videos in the DB

Trending Hashtags Microservice (THM):

This microservice handles the creation of a list of the top 10 trending hashtags based on the likes received the videos. To
achieve this, THM uses two kafka streams consumers that compute the top 10 in a rolling window of one hour, a kafka
subscriber that saves the list in the DB, and a kafka producer that is scheduled to update the rolling window every
minute, even if there are no new liked videos. In more details, these are the workflow of THM:

Subscription Microservice (SM):

• GET “/”: Returns all the users in SM’s database (used as a healthcheck)

• GET “/{userId}: Returns all the hashtags a user is subscribed to

• PUT “/{userId}/subscribe”: Subscribes the user to the hashtag specified in the body

• PUT “/{userId}/unsubscribe”: Unsubscribes from the hashtag specified in the body

• GET “/{userId/recommandations}”: Gets the recommendations for a given user and hashtag


Usage:

1. Use “gradlew dockerBuild” to build the docker images
2. User “docker compose up -d kafka-0 kafka-1 kafka-2 db” to bring up the kafka cluster and the db
3. Run the following commands to create all the kafka topics, replacing TOPIC_NAME with each of the
following: video_posted, video_watched, video_liked, video_disliked, user_created, hashtag_liked,
trending_hashtags_by_hour, user_subscribed, user_unsubscribed
docker exec -e JMX_PORT= microservices-kafka-0-1 kafka-topics.sh --bootstrap-server kafka-0:9092 --create
--topic TOPIC_NAME --replication-factor 3 --partitions 6
4. Run docker compose up to bring everything else up
