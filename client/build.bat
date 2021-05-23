@REM call mvn clean package
call docker build -t mirovan/sovkombank .
call docker tag mirovan/sovkombank stor.highloadcup.ru/phone_book/right_buffalo
call docker push stor.highloadcup.ru/phone_book/right_buffalo

@REM docker run -dp 8080:8080 -it -e ADDRESS=192.168.1.143 mirovan/hl2021