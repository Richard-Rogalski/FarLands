package ski.rrogal.farlands.mixins;

import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
//import ski.rrogal.farlands.FarLands;
import com.kpabr.FarLands.FarLands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkProviderGenerate.class)
public class MixinChunkProviderGenerate {

    @Inject(method = "func_147423_a", at = @At("HEAD"))
    private void injectCustomCode(int p_147423_1_, int p_147423_2_, int p_147423_3_, CallbackInfo ci) {
        // Your custom code here
        // This code will be executed at the beginning of the "func_147423_a" method.
        // You can add your own logic or calls to other methods here.
		if(FarLands.threshold > 0){
 	   		if(Math.abs(p_147423_1_) > FarLands.threshold/4){
 	   			p_147423_1_ = (int)(p_147423_1_+(Math.copySign(((12550820-FarLands.threshold)/4), p_147423_1_)));
 	   		}
 	   		if(Math.abs(p_147423_3_) > FarLands.threshold/4){
 	   			p_147423_3_ = (int)(p_147423_3_+(Math.copySign(((12550820-FarLands.threshold)/4), p_147423_3_)));
 	   		}
     	}
        System.out.println("Injecting custom code into ChunkProviderGenerate.func_147423_a");
    }
}
