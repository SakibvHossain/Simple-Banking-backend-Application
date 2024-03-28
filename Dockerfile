FROM eclipse-temurin:17
LABEL authors="Sakib Hossain"

ENTRYPOINT ["top", "-b"]

# banking application [clean, package] to make a jar file of the application