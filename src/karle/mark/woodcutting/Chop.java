package karle.mark.woodcutting;
import karle.mark.all.Task;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Chop extends Task{
	final int[] treeIds = {11756};
	public Chop(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.inventory.select().count() < 28
				&& !ctx.objects.select().id(treeIds).isEmpty()
				&& ctx.players.local().animation() == -1;
				
	}

	@Override
	public void execute() {
		GameObject tree = ctx.objects.nearest().poll();
		if(tree.inViewport()){
			tree.interact("Chop");
		}else{
			ctx.movement.step(tree);
			ctx.camera.turnTo(tree);
		}
		Condition.sleep(1000);
	}

}
