# Notification
##	Данное приложение позволяет пользователю отправить себе уведомление. Так же в положении присутствует сортировка уведомлений на те которые уже были высланы, и на те которые ещё нужно отправить пользователю
### 	В основе лежат такие технологии как: Room Database, Fragment, View Model, Live Data, Notification Compat, coroutines, mvvm
+ 	Приложение состоит из двух Layout и двух фрагментов
	
+    Первый Layout хранит в себе два фрагмента сортировки уведомлений


+	 На первом фрагменте отображаются уведомления, которые не были высланы, на втором которые уже были показаны пользователю

![Screenshot_2022-04-16-13-11-09-793_com example 1](https://user-images.githubusercontent.com/80197239/163664091-d81b5f54-8470-4592-afd5-cd2b5f1b5923.png)
![Screenshot_2022-04-16-13-11-38-320_com example 1](https://user-images.githubusercontent.com/80197239/163664094-6f96b808-47ee-46eb-8b22-8b354632a99f.png)

+    Второй Layout добавляет запись и время уведомления в room Database
 	
  ![Screenshot_2022-04-16-13-10-50-112_com example 1](https://user-images.githubusercontent.com/80197239/163664126-6c7db7b7-2867-4905-8a98-5ee69f887444.png)
![Screenshot_2022-04-16-13-10-54-808_com example 1](https://user-images.githubusercontent.com/80197239/163664130-bd10ac16-9df9-47ed-a161-48625b89fb3b.png)
![Screenshot_2022-04-16-13-11-04-925_com example 1](https://user-images.githubusercontent.com/80197239/163664133-866b53ea-e14c-4974-b527-01bc3b982f95.png)
