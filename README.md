# SpringCommerce

SpringCommerce là một ứng dụng web đơn giản dùng để bán những sản phẩm Game bản quyền của Puahuhu Store. Vì nhu cầu về việc mua game bản quyền đang ngày càng gia tăng, Puahuhu Store đã xây dựng SpringCommerce để đáp ứng với các chức năng còn hạn chế.

## Mô hình phát triển phần mềm

Dự án SpringCommerce của Puahuhu Store được áp dụng mô hình phát triển phần mềm Waterfall, phù hợp cho một hệ thống đơn giản và yêu cầu rõ ràng. Mô hình Waterfall có các giai đoạn chính bao gồm:

1. **Phân tích yêu cầu**: Thu thập và xác định các yêu cầu từ đề bài. Dự án sẽ hỗ trợ hiển thị sản phẩm, lọc theo tiêu chí và đặt hàng qua giỏ hàng.
   
2. **Thiết kế hệ thống**: Thiết kế cấu trúc cơ bản của ứng dụng web, bao gồm cơ sở dữ liệu và giao diện người dùng.

3. **Lập trình**: Triển khai các tính năng cốt lõi như hiển thị sản phẩm, lọc, và quản lý giỏ hàng.

4. **Kiểm thử**: Kiểm thử toàn bộ ứng dụng để đảm bảo tính năng hoạt động đúng theo yêu cầu.

5. **Triển khai**: Sau khi kiểm thử thành công, sản phẩm sẽ được triển khai ra thị trường.

## Chức năng chính

1. **Hiển thị sản phẩm**: Giao diện đơn giản hiển thị toàn bộ Game bản quyền mà Puahuhu Store cung cấp. Người dùng có thể lọc và tìm kiếm sản phẩm theo các tiêu chí như danh mục, tên, giá, thể loại và studio làm ra nó.

2. **Giỏ hàng và đặt hàng**: Người dùng có thể thêm sản phẩm vào giỏ hàng và tiến hành đặt hàng.

3. **Thanh toán khi nhận hàng**: Hiện tại ứng dụng chưa hỗ trợ thanh toán trực tuyến, người dùng sẽ thanh toán bằng tiền mặt khi sản phẩm được giao đến.

## Nguyên tắc và thực hành

- **Nguyên tắc đơn giản hóa**: Chỉ tập trung vào các tính năng thiết yếu để đáp ứng yêu cầu cơ bản của một MVP.
- **Tính mở rộng**: Thiết kế hệ thống đơn giản nhưng dễ dàng mở rộng thêm các tính năng khác trong tương lai.
- **Tách biệt logic và giao diện**: Các lớp chức năng và giao diện được thiết kế riêng biệt để dễ dàng bảo trì và nâng cấp.

## Cấu trúc mã nguồn

Dự án SpringCommerce sử dụng cấu trúc chuẩn của Maven với các thư mục và gói như sau:

- **com.example.SpringCommerce**: Đây là gói chính của dự án, chứa các thành phần chính theo mô hình MVC (Model-View-Controller) để tổ chức mã nguồn một cách khoa học và dễ bảo trì.

  - **controller**: Thư mục này chứa các lớp điều khiển, chịu trách nhiệm xử lý các yêu cầu từ phía người dùng, gọi các dịch vụ cần thiết và trả kết quả về cho giao diện người dùng.

  - **model**: Chứa các lớp mô hình đại diện cho các đối tượng dữ liệu trong ứng dụng. Các lớp này ánh xạ với các bảng trong cơ sở dữ liệu và được sử dụng để lưu trữ và truy xuất dữ liệu.

  - **repository**: Thư mục này chứa các lớp thao tác với cơ sở dữ liệu, sử dụng Spring Data JPA để quản lý dữ liệu một cách hiệu quả và tách biệt các thao tác cơ sở dữ liệu khỏi logic của ứng dụng.

  - **service**: Thư mục này chứa các lớp dịch vụ xử lý logic nghiệp vụ của ứng dụng, đóng vai trò trung gian giữa controller và repository, thực hiện các xử lý cần thiết trước khi gửi dữ liệu tới các thành phần khác.

  - **SpringCommerceApplication.java**: Đây là điểm khởi động của ứng dụng Spring Boot, chứa các phương thức để thêm dữ liệu vào cơ sở dữ liệu cũng như là phương thức để chạy ứng dụng.

- **resources**: Thư mục này chứa các tài nguyên cần thiết cho ứng dụng.

  - **static**: Thư mục này chứa các tệp tĩnh bao gồm:
    - **css**: Chứa các tệp CSS để định dạng giao diện người dùng.
    - **js**: Chứa các tệp JavaScript để bổ sung chức năng động cho trang web.
    - **images**: Chứa các hình ảnh sử dụng trong giao diện.

  - **templates**: Thư mục này chứa các tệp HTML sử dụng để hiển thị giao diện người dùng. Spring Boot sẽ sử dụng các tệp này để tạo trang web động, cho phép khách hàng xem sản phẩm, lọc sản phẩm, thêm vào giỏ hàng và thực hiện các thao tác đặt hàng.
 
  - **application.properties**: Đây là nơi sẽ cấu hình cơ sở dữ liệu cho ứng dụng.

Cấu trúc này giúp ứng dụng dễ dàng bảo trì, mở rộng và đảm bảo tính nhất quán trong quy trình phát triển phần mềm theo mô hình Waterfall.

## Các bước để chạy ứng dụng

- Đầu tiên ta sẽ nhấn vào phần Download ZIP để tải dự án về máy
  
     ![image](https://github.com/user-attachments/assets/da6e284a-6bd1-45d9-8382-e6fa2a4bf4f8)

- Sau đó hãy giải nén và mở dự án bằng IntelliJ

     ![image](https://github.com/user-attachments/assets/33f618c4-b291-4c8f-8139-934953b82edb)

- Tiếp đến ta mở Xampp để kết nối vào cơ sở dữ liệu
  
     ![Screenshot 2024-11-14 145315](https://github.com/user-attachments/assets/14e453bf-6268-4d7d-be6f-97fe03e9c21b)

- Kế đến ta sẽ nhấn vào nút chạy như ảnh dưới

     ![image](https://github.com/user-attachments/assets/ec6ca7a3-8005-4cf5-82b8-c6c37c11ba65)

- Cuối cùng ta vào Google Chrome hoặc các trình duyệt khác và chỉa tới đường dẫn localhost:8080, nếu như thành công ta sẽ được dẫn đến trang đăng nhập

     ![image](https://github.com/user-attachments/assets/8d443c05-51ae-4b87-8eb4-f1f3d21f8499)

## Kiểm thử API

- Đối với Product ta sẽ có các phương thức:
  
   - **GET**:
     
      - http://localhost:8080/api/products: dùng để hiện thị tất cả các sản phẩm có trong cơ sở dữ liệu

         ![image](https://github.com/user-attachments/assets/fb416c31-f115-4b0b-918f-106c977a7cd8)

      - http://localhost:8080/api/product/{id}: dùng để hiện thị sản phẩm ứng với id của sản phẩm có trong cơ sở dữ liệu

         ![image](https://github.com/user-attachments/assets/d495f96f-f85f-4480-b784-8bf620ae0bb4)

   - **POST**: http://localhost:8080/api/product: dùng để thêm sản phẩm vào cơ sở dữ liệu
 
      - Cơ sở dữ liệu hiện tại đang có id lớn nhất là 10:
    
        ![image](https://github.com/user-attachments/assets/933c7042-534c-4460-a1ff-296fb807b02d)
          
      - Ta sẽ thêm như sau bằng phương thức **POST**:
    
        ![image](https://github.com/user-attachments/assets/74b91001-8087-4463-a492-9df8b86e5551)

      - Cơ sở dữ liệu sau khi thêm sản phẩm mới vào:
    
        ![image](https://github.com/user-attachments/assets/1f715aac-1129-44af-84be-90da22dcd625)

     - Cuối cùng là ở localhost ta cũng đã thêm sản phẩm Assassin's Creed II sau khi sử dụng phương thức **POST**

        ![image](https://github.com/user-attachments/assets/a4cc77ea-c00e-4e4a-85e0-0f2cc7d35cd1)

   - **PUT**: http://localhost:8080/api/product/{id}: dùng để chỉnh sửa sản phẩm ứng với id sản phảm phẩm có trong cơ sở dữ liệu

      - Ta sẽ sử dụng phương thức **GET** để lấy sản phẩm với id tùy chọn, ảnh dưới đây sẽ chọn id là 11:
    
        ![image](https://github.com/user-attachments/assets/75c3b02a-86c4-4d39-8f99-985aa278d707)

      - Sau đó sử dụng phương thức **PUT** để chỉnh sửa như sau, ảnh dưới sẽ chỉnh sửa giá từ 599.000đ thành 600.000đ:
    
        ![image](https://github.com/user-attachments/assets/19654a42-7217-4c84-a2bb-e19134399e69)

      - Tiếp đến ta sẽ kiểm tra lại bằng phương thức **GET**:
    
        ![image](https://github.com/user-attachments/assets/b9fc8b2f-199e-4e68-b2e7-750630818ffc)

      - Cuối cùng là kiểm tra trên localhost:
    
        ![image](https://github.com/user-attachments/assets/f023e086-bd62-4378-bd97-c297a1641f48)

   - **DELETE**: http://localhost:8080/api/product/{id}: dùng để xóa sản phẩm ứng với id sản phẩm có trong cơ sở dữ liệu
 
      - Ta sẽ sử dụng phương thức **DELETE** để xóa sản phẩm với id bất kì, ảnh dưới sẽ chọn id là 11 tương ứng với Assassin's Creed II:
    
        ![image](https://github.com/user-attachments/assets/d1f724c1-75d1-4ef9-bd54-fd979d5f28ef)

      - Sau khi xóa ta kiểm tra lại bằng phương thức **GET**:
    
        ![image](https://github.com/user-attachments/assets/4905e514-5eec-4aeb-a993-72c911d777cb)

      - Cuối cùng ta kiểm tra trên localhost:
    
        ![image](https://github.com/user-attachments/assets/dafbf096-eb45-48e9-8f41-5596d256aa05)

- Đối với Order ta sẽ có các phương thức:
  
   - **GET**:
 
     - http://localhost:8080/api/orders: dùng để hiện thị tất cả các hóa đơn có trong cơ sở dữ liệu

         ![image](https://github.com/user-attachments/assets/1fb67824-df1c-4bbb-bb6a-7d4519ea5584)

      - http://localhost:8080/api/orders/{id}: dùng để hiện thị hóa đơn ứng với id của hóa đơn có trong cơ sở dữ liệu

         ![image](https://github.com/user-attachments/assets/59bee0ae-48cf-4d94-9215-baf2d6c2162f)

   - **DELETE**: http://localhost:8080/api/order/{id}: dùng để xóa hóa đơn ứng với id hóa đơn có trong cơ sở dữ liệu
 
      - Ta sẽ sử dụng phương thức **DELETE** để xóa hóa đơn với id bất kì, ảnh dưới sẽ chọn id là 2:
 
        ![image](https://github.com/user-attachments/assets/2a9320d9-2f01-42a1-aded-831c48135a0a)

      - Sau khi xóa ta kiểm tra lại bằng phương thức **GET**:
    
        ![image](https://github.com/user-attachments/assets/676e064b-dfbc-47f6-95fa-99465e5cebb7)

      - Cuối cùng ta kiểm tra trên localhost:
    
        ![image](https://github.com/user-attachments/assets/12b5f591-6d46-4604-a5b8-a73b8118670f)
