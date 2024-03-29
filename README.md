# BomberManGame_Team06_1.0
## Video Demo [Link](https://drive.google.com/file/d/1uDTfsIm_gDg73SSgCmqCaCKMARwt9ZSs/view?usp=share_link)
## Về trò chơi


![Tutorial](https://user-images.githubusercontent.com/81580234/198694556-f693c04e-1888-4c11-8492-91b24c6ff902.png)
- ![](res/sprites/player_down.png) *Bomber* là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi. 
- ![](res/sprites/balloom_left1.png) *Enemy* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
- ![](res/sprites/bomb.png) *Bomb* là đối tượng mà Bomber sẽ đặt và kích hoạt tại các ô Grass. Khi đã được kích hoạt, Bomber và Enemy không thể di chuyển vào vị trí Bomb. Tuy nhiên ngay khi Bomber vừa đặt và kích hoạt Bomb tại ví trí của mình, Bomber có một lần được đi từ vị trí đặt Bomb ra vị trí bên cạnh. Sau khi kích hoạt 2s, Bomb sẽ tự nổ, các đối tượng *Flame* ![](res/sprites/explosion_horizontal.png) được tạo ra.


- ![](res/sprites/grass.png) *Grass* là đối tượng mà Bomber và Enemy có thể di chuyển xuyên qua, và cho phép đặt Bomb lên vị trí của nó
- ![](res/sprites/wall.png) *Wall* là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Enemy không thể di chuyển vào đối tượng này
- ![](res/sprites/brick.png) *Brick* là đối tượng được đặt lên các ô Grass, không cho phép đặt Bomb lên nhưng có thể bị phá hủy bởi Bomb được đặt gần đó. Bomber và Enemy thông thường không thể di chuyển vào vị trí Brick khi nó chưa bị phá hủy.


- ![](res/sprites/portal.png) *Portal* là đối tượng được giấu phía sau một đối tượng Brick. Khi Brick đó bị phá hủy, Portal sẽ hiện ra và nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal.

Các *Item* cũng được giấu phía sau Brick và chỉ hiện ra khi Brick bị phá hủy. Bomber có thể sử dụng Item bằng cách di chuyển vào vị trí của Item. Thông tin về chức năng của các Item được liệt kê như dưới đây:
- ![](res/sprites/powerup_speed.png) *SpeedItem* Khi sử dụng Item này, Bomber sẽ được tăng vận tốc di chuyển thêm một giá trị thích hợp
- ![](res/sprites/powerup_flames.png) *FlameItem* Item này giúp tăng phạm vi ảnh hưởng của Bomb khi nổ (độ dài các Flame lớn hơn)
- ![](res/sprites/powerup_bombs.png) *BombItem* Thông thường, nếu không có đối tượng Bomb nào đang trong trạng thái kích hoạt, Bomber sẽ được đặt và kích hoạt duy nhất một đối tượng Bomb. Item này giúp tăng số lượng Bomb có thể đặt thêm một.

Có nhiều loại Enemy trong Bomberman, tuy nhiên trong phiên bản này chỉ yêu cầu cài đặt hai loại Enemy dưới đây (nếu cài đặt thêm các loại khác sẽ được cộng thêm điểm):
- ![](res/sprites/balloom_left1.png) *Balloom* là Enemy đơn giản nhất, di chuyển ngẫu nhiên với vận tốc cố định
- ![](res/sprites/oneal_left1.png) *Oneal* có tốc độ di chuyển thay đổi, lúc nhanh, lúc chậm và di chuyển "thông minh" hơn so với Balloom (biết đuổi theo Bomber)

## Mô tả game play, xử lý va chạm và xử lý bom nổ
![Level_Background](https://user-images.githubusercontent.com/81580234/198695582-18f717b3-2fbe-412e-9316-7a94ef90f2f1.jpg)

- Trong một màn chơi, Bomber sẽ được người chơi di chuyển, đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả Enemy và tìm ra vị trí Portal để có thể qua màn mới
- Bomber sẽ bị giết khi va chạm với Enemy hoặc thuộc phạm vi Bomb nổ. Lúc đấy trò chơi kết thúc.
- Enemy bị tiêu diệt khi thuộc phạm vi Bomb nổ
- Một đối tượng thuộc phạm vi Bomb nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng Bomb nổ.

- Khi Bomb nổ, một Flame trung tâm![](res/sprites/bomb_exploded.png) tại vị trí Bomb nổ và bốn Flame tại bốn vị trí ô đơn vị xung quanh vị trí của Bomb xuất hiện theo bốn hướng trên![](res/sprites/explosion_vertical.png)/dưới![](res/sprites/explosion_vertical.png)/trái![](res/sprites/explosion_horizontal.png)/phải![](res/sprites/explosion_horizontal.png). Độ dài bốn Flame xung quanh mặc định là 1 đơn vị, được tăng lên khi Bomber sử dụng các FlameItem.
- Khi các Flame xuất hiện, nếu có một đối tượng thuộc loại Brick/Wall nằm trên vị trí một trong các Flame thì độ dài Flame đó sẽ được giảm đi để sao cho Flame chỉ xuất hiện đến vị trí đối tượng Brick/Wall theo hướng xuất hiện. Lúc đó chỉ có đối tượng Brick/Wall bị ảnh hưởng bởi Flame, các đối tượng tiếp theo không bị ảnh hưởng. Còn nếu vật cản Flame là một đối tượng Bomb khác thì đối tượng Bomb đó cũng sẽ nổ ngay lập tức.


## UML 
![image](https://user-images.githubusercontent.com/81580234/198875944-9ce292ed-a5a9-4c2a-a4a3-ae156044125c.png)

## Slide thuyết trình
[Bomberman06.pptx](https://github.com/trandung2801/BomberManGame_Team06_1.0/files/9895680/Bomberman06.pptx)

## Nhóm phát triển
* Trần Anh Dũng
* Nguyễn Văn Thành
* Hoàng Hải Long
## Hướng dẫn cài đặt game
- Sau khi clone repo về hãy làm theo hướng dẫn sau đây
![Ảnh chụp màn hình (53)](https://user-images.githubusercontent.com/98462569/198703562-a6b41f3b-3666-4ef1-b5e4-d40dbc7b7df8.png)
- Chọn file và chọn close project
![Ảnh chụp màn hình (54)](https://user-images.githubusercontent.com/98462569/198703668-da1d828e-b1ff-4017-ba5b-b45ff312c0f0.png)
- Chọn open bên góc phải màn hình
![Ảnh chụp màn hình (55)](https://user-images.githubusercontent.com/98462569/198703842-d1643466-9d5b-4b11-aaf6-bcc2c7760620.png)
- Nhấn đúp vào thư mục vừa tải repo về
![Ảnh chụp màn hình (56)](https://user-images.githubusercontent.com/98462569/198703957-4a7d9509-6ec5-42c3-8bef-a91c5215972b.png)
- Chọn vào BomberManGame_Team06_1.0 và chọn OK
![Ảnh chụp màn hình (57)](https://user-images.githubusercontent.com/98462569/198704126-ba5c38e5-4001-43fc-ada7-41d0d72a8364.png)
- Hảy chắc chắn rằng bạn đang dùng JDK 1.8 và language level 8
![Ảnh chụp màn hình (58)](https://user-images.githubusercontent.com/98462569/198704248-c2c444eb-53ee-4898-bce0-e2bea38011bd.png)

