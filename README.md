# BomberManGame_Team06_1.0
![Menu_Background](https://user-images.githubusercontent.com/81580234/194708275-75b1d9da-3ff3-48a9-aafe-f0a913aa9481.jpg)
- *Bomber* là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi.
- *Monster* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Monster có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Monster. Các loại Monster sẽ được mô tả cụ thể ở phần dưới.
- *Bomb* là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass. Khi đã được kích hoạt, Bomber và Monster không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s, Bomb sẽ tự nổ, các đối tượng *Flame* được tạo ra.
- *Grass* là đối tượng mà Bomber và Monster có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó
-*Wall* là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Monster không thể di chuyển vào đối tượng này
- *Brick* là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó. Bomber và Monster thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.
- *Portal* là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Monster đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.

Các *Item* cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như dưới đây:

- *SpeedItem* Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp
- *FlameItem* Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn)
- *BombItem* Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt, Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb. Item này giúp tăng số lượng Bomb có thể đặt thêm một.
- *PassWallItem* Khi sử dụng Item này, Bomber sẽ có khả năng đi qua *Brick* cho đến khi hết game.

Có nhiều loại Monster trong Bomberman:

- *Balloon* là Monster đơn giản nhất. Quỹ đạo di chuyển là sang trái hoặc phải với vận tốc là 1
- *Oneal* có tốc độ di chuyển là 2 và quỹ đạo di chuyển tương tự balloon
- *Doll* có quỹ đạo di chuyển ngẫu nhiên với tốc độ bằng 1
- *Minvo* có quỹ đạo di chuyển ngẫu nhiên với tốc độ bằng 2
- *Kondoria* có quỹ đạo di chuyển ngẫu nhiên với vận tốc bằng và có thêm khả năng đi xuyên brick
- *Bat Monster* quỹ đạo di chuyển ngẫu nhiên với tốc độ bằng 2 và có khả năng phát hiện bom trước 1 ô
- *Dragon* quỹ đạo di chuyển ngẫu nhiên với tốc độ bằng 3 và có khả năng phát hiện bom trước 1 ô. Khi chết thì biến thành ngọn lửa và có quỹ đạo di chuyển giống *Bat Monster*
- *Phoenix* quỹ đạo di chuyển ngẫu nhiên với tốc độ bằng 3 và có khả năng phát hiện bom trước 1 ô. Khi chết sẽ biến thành ngọn lửa (tốc độ di chuyển 4) và sau khoảng 10s sẽ hồi sinh thành *Phoenix*

## Mô tả game play, xử lý va chạm và xử lý bom nổ

- Trong một màn chơi, Bomber sẽ được người chơi di chuyển, đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả Monster và tìm ra vị trí Portal để có thể qua màn mới
- Bomber sẽ bị giết khi va chạm với Monster hoặc thuộc phạm vi Bomb nổ. Lúc đấy trò chơi kết thúc.
- Monster bị tiêu diệt khi thuộc phạm vi Bomb nổ
- Một đối tượng thuộc phạm vi Bomb nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng Bomb nổ.

- Khi Bomb nổ, một Flame trung tâm tại vị trí Bomb nổ và bốn Flame tại bốn vị trí ô đơn vị xung quanh vị trí của Bomb xuất hiện theo bốn hướng trên/dưới/trái/phải. Độ dài bốn Flame xung quanh mặc định là 1 đơn vị, được tăng lên khi Bomber sử dụng các FlameItem.
- Khi các Flame xuất hiện, nếu có một đối tượng thuộc loại Brick/Wall nằm trên vị trí một trong các Flame thì độ dài Flame đó sẽ được giảm đi để sao cho Flame chỉ xuất hiện đến vị trí đối tượng Brick/Wall theo hướng xuất hiện. Lúc đó chỉ có đối tượng Brick/Wall bị ảnh hưởng bởi Flame, các đối tượng tiếp theo không bị ảnh hưởng. Còn nếu vật cản Flame là một đối tượng Bomb khác thì đối tượng Bomb đó cũng sẽ nổ ngay lập tức.
