REST, DTO. В этом проекте мы создаем две программы которые обмениваютcя данными между собой с помощью REST API и data transfer object. Первое спринг приложение - это сервер, который хранит информацию о 
датчиках измерений и сами измерения. Второе приложение это клиент, оно отсылает данные первому - название датчика и его измерения погоды. 
В измерении содержится информация о том идет ли дождь, температура воздуха и время измерения. Сервер сохраняет эти данные в базу данных. Затем клиент получает эти данные от сервера по запросу. 
