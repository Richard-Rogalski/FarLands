package ski.rrogal.farlands.mixins;

import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderEnd;
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
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import org.spongepowered.asm.mixin.injection.invoke.arg.*;
import net.minecraft.block.Block;
import com.kpabr.FarLands.FarLands;

@Mixin(ChunkProviderEnd.class)
public class MixinChunkProviderEnd {
/*
    @Inject(method = "func_147419_a", at = @At("HEAD"))
    private void injectCustomCode(int p_147419_1_, int p_147419_2_, Block[] p_147419_3_, CallbackInfo ci) {
		if(FarLands.thresholdNether > 0){
 	   		if(Math.abs(p_147419_1_) > FarLands.thresholdNether/16){
 	   			p_147419_1_ = (int)(p_147419_1_+(Math.copySign(((12550820-FarLands.thresholdNether/16)), p_147419_1_)));
 	   		}
 	   		if(Math.abs(p_147419_2_) > FarLands.thresholdNether/16){
 	   			p_147419_2_ = (int)(p_147419_2_+(Math.copySign(((12550820-FarLands.thresholdNether/16)), p_147419_2_)));
 	   		}
     	}
        System.out.println("Injecting custom code into ChunkProviderGenerate.func_147423_a" + FarLands.thresholdNether + " " + p_147419_1_ + " " + p_147419_2_);
    }
*/
	@ModifyArgs(method = "provideChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/ChunkProviderEnd;func_147420_a(II[Lnet/minecraft/block/Block;[Lnet/minecraft/world/biome/BiomeGenBase;)V"))
    private void thisShouldWork2(Args args) {
	System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ" + FarLands.thresholdEnd + "   " + args.get(0) + "  " + args.get(1));
        if(FarLands.thresholdEnd > 0){
            int i = (int)args.get(0);
            if(Math.abs(args.get(0)) > FarLands.thresholdEnd/16){
                i = (int)(i + (Math.copySign(((12550820 - FarLands.thresholdEnd)/16), i)));
                args.set(0, i);
            }
            i = (int)args.get(1);
            if(Math.abs(args.get(1)) > FarLands.thresholdEnd/16){
                i = (int)(i + (Math.copySign(((12550820 - FarLands.thresholdEnd)/16), i)));
                args.set(1, i);
            }
        }
    }
}
