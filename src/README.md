README for CMDAnimator

-Manual test Sheet for main -
GameCanvas GameCanvas = GUI.getScreen();
		// manual test code
		GameCanvas.addText(new CanvasText("1Hello world", new Point(25, 25)));
		GameCanvas.addText(new CanvasText("polly world", new Point(50, 55)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("2Hello world2", new Point(30, 30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("3Hello world2", new Point(35, 30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("4Hello world2", new Point(40, 30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("--Hello world2--", new Point(45, 30)));
		GameCanvas.clearCanvas();
		GameCanvas.addText(new CanvasText("NOT Hello world2", new Point(45, 30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("5Hello world2", new Point(50, 30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("6Hello world2", new Point(55, 30)));
		Animation.addFrameToAnimation(GUI);
		GameCanvas = GUI.getScreen();
		GameCanvas.addText(new CanvasText("7Hello world2", new Point(55, 30)));
		Animation.addFrameToAnimation(GUI);
		Animation.playAnimation(GUI);
		//Animation.playAnimation(GUI);
		// GameCanvas.deleteText("Hello world");