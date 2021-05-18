# Обмен данными по сети. Библиотека Retrofit
### Retrofit

22.7
Цель задания
Вы поработаете с API GitHub, используя библиотеку Retrofit.
Что нужно сделать
1. Зарегистрируйте приложение в GitHub, чтобы настроить аутентификацию
пользователя. Почитайте, как это сделать:
https://docs.google.com/document/d/1rhQiPXJQibAwtxJUENjQqyQ8uB_Gf2JdU_nqg
TgStIg/edit?usp=sharing .
2. Ознакомьтесь с преднастроенным проектом. В нём реализованы заглушки для
аутентификации пользователя.
3. Установите значения client id, client secret, Authorization callback url
зарегистрированного приложения в объект AuthConfig в преднастроенном
проекте.
4. Запустите приложение, убедитесь, что аутентификация в GitHub работает. Для
этого после нажатия кнопки «Войти» введите логин и пароль GitHub и
разрешите приложению, созданному в пункте 1, доступ к данным.
5. Откройте класс AuthRepository. Найдите место c пометкой TODO. Сохраните
access token в объект Singleton.
6. Создайте okhttp client. Добавьте интерцептор, который для каждого сетевого
запроса будет добавлять заголовок авторизации c access token из предыдущего
пункта, если он установлен. Пример:
https://docs.github.com/en/rest/overview/resources-in-the-rest-api#oauth2-token-sent
-in-a-header .
7. Создайте и сохраните в Singleton инстанс Retrofit. Создайте интерфейс для
описания взаимодействия с сервером. Все дальнейшие обращения к серверу
описывайте в этом интерфейсе.
8. Реализуйте экран просмотра информации о текущем пользователе.
Используйте заглушку CurrentUserFragment. Для получения информации о
текущем пользователе используйте метод API:
https://docs.github.com/en/rest/reference/users#get-the-authenticated-user .
9. Реализуйте экран списка публичных репозиториев. Используйте заглушку
RepositoryListFragment. Для получения списка публичных репозиториев
используйте метод:
https://docs.github.com/en/rest/reference/repos#list-public-repositories .
10. По нажатию на репозиторий реализуйте переход на экран детальной
информации о репозитории. На экране детальной информации должна быть
возможность поставить звёздочку репозиторию или снять звёздочку, если она
была проставлена раньше.
— Чтобы проверить, проставлена ли звёздочка для репозитория, используйте
метод:
https://docs.github.com/en/rest/reference/activity#check-if-a-repository-is-starred-by-t
he-authenticated-user .
— Чтобы поставить звёздочку репозиторию, используйте метод:
https://docs.github.com/en/rest/reference/activity#star-a-repository-for-the-authenticat
ed-user .
— Чтобы снять звездочку с репозитория, используйте метод:
https://docs.github.com/en/rest/reference/activity#unstar-a-repository-for-the-authenti
cated-user .
После простановки или снятия звезды убедитесь, что список репозиториев со
звёздочками изменился для авторизованного пользователя. Список
репозиториев со звёздочкой можно получить с помощью метода:
https://docs.github.com/en/rest/reference/activity#list-repositories-starred-by-the-auth
enticated-user или в веб-странице: https://github.com/<ваш username>?tab=stars .
11. Задание по желанию: реализуйте обновление информации текущего
пользователя с помощью метода:
https://docs.github.com/en/rest/reference/users#update-the-authenticated-user .