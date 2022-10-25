package bomberman.GameItems;

import javafx.scene.image.Image;
import bomberman.GameEntities.GameEntity;
public abstract class Item extends GameEntity {
	
	public Item(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		// TODO Auto-generated constructor stub
	}

	protected String id;
	protected int timeToDisappear = 500;


	@Override
	public void update() {
		timeToDisappear--;
		if (timeToDisappear == 0) {
			this.setImg(null);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		

}