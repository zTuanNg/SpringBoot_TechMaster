Chức năng : 

    const URL_API = "https://mini-courses-website.herokuapp.com";

1.
-  Lấy tất cả các khoá học || Lọc theo topic , keyword
- url_api = "{URL_API}/api/v1/courses?topic=${topic}&keyword=${keyword}"

2.
-  Lấy tất cả khoá học theo type : onlab / online || Lọc theo topic, keyword
- Onlab => url_api = "${URL_API}/api/v1/courses/onlab?topic=${topic}&keyword=${keyword}"
- Online => url_api = "${URL_API}/api/v1/courses/online?topic=${topic}&keyword=${keyword}"

3.
- Lấy chi tiết khoá học 
- url_api = "${URL_API}/api/v1/course-detail/${id}"