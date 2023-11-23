# English Dictionary App
This is an open source Dictionary App

## Hướng dẫn cài đặt
### Bước 1: Cài đặt [JDK 20.0.2](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)
- Truy cập vào [Java Archive Downloads](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html), tài xuống phiên bản phù hợp với thiết bị của bạn và cài đặt.
    **Lưu ý**: Cài đặt chính xác phiên bản [**JDK 20.0.2**](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html) để chương trình hoạt động một cách chính xác nhất.
### Bước 2: Cài đặt
#### Cách 1: Cài đặt từ file installer
- Truy cập [link này](https://github.com/nguyen28122004/hara-dict-app/releases/tag/installer) và tải về máy file cài đặt `English-Dictionary-Installer.exe`
- Chạy file vừa tải về, tiến hành cài đặt
- Mở Start, tìm kiếm "English Dictionary App" để khởi động chương trình

#### Cách 2: Chạy chương trình bằng mã nguồn / file jar
- Clone repo từ nhánh master về thư mục trên máy bạn
    ```
    git clone https://github.com/nguyen28122004/hara-dict-app.git
    ```
- Mở thư mục chứa mã nguồn vừa clone về
**Bằng mã nguồn**: Mở dưới dạng Java Project với VS Code và chạy.
**Bằng file jar**: Mở `cmd` từ thư mục chứa mã nguồn và thực hiện lệnh sau:
    ```
    java --enable-preview -jar --module-path lib/javafx-sdk-21/lib --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.graphics --add-exports javafx.graphics/com.sun.glass.ui=ALL-UNNAMED hara-dict-app.jar
    ```



## Một số vấn đề có thể gặp khi cài đặt
- Khi chạy chương trình báo lỗi `This application requires a Java Runtime Environment`, hãy thực hiện bước 1. Nếu đã thực hiện bước 1 và vẫn nhận được lỗi này hãy chỉnh sửa biến môi trường trong cài đặt.
- Khi khởi động chương trình nhưng không thấy chương trình khởi động hoặc hiện thông báo, thực hiện lại bước 1 và chỉnh sửa biến môi trường trong cài đặt.
- **Cách chỉnh sửa biến môi trường**
    Mở **Start** bằng cách bấm phím Window trên bàn phím
    Tìm kiếm `cmd` và chạy dưới **Command Prompt** dưới quyền **Administrator**
    ```
    setx -m JAVA_HOME <your JDK 20 install path>
    ```
    **JDK 20 install path** mặc định là `"C:\Program Files\Java\jdk-20"`


