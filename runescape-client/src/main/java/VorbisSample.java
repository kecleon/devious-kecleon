import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;
@ObfuscatedName("ah")
@Implements("VorbisSample")
public class VorbisSample extends Node {
    @ObfuscatedName("i")
    @Export("VorbisSample_bytes")
    static byte[] VorbisSample_bytes;

    @ObfuscatedName("w")
    @Export("VorbisSample_byteOffset")
    static int VorbisSample_byteOffset;

    @ObfuscatedName("v")
    @Export("VorbisSample_bitOffset")
    static int VorbisSample_bitOffset;

    @ObfuscatedName("a")
    @Export("VorbisSample_blockSize0")
    static int VorbisSample_blockSize0;

    @ObfuscatedName("y")
    @Export("VorbisSample_blockSize1")
    static int VorbisSample_blockSize1;

    @ObfuscatedName("u")
    @ObfuscatedSignature(descriptor = "[Lax;")
    @Export("VorbisSample_codebooks")
    static VorbisCodebook[] VorbisSample_codebooks;

    @ObfuscatedName("h")
    @ObfuscatedSignature(descriptor = "[Las;")
    @Export("VorbisSample_floors")
    static VorbisFloor[] VorbisSample_floors;

    @ObfuscatedName("q")
    @ObfuscatedSignature(descriptor = "[Lat;")
    @Export("VorbisSample_residues")
    static VorbisResidue[] VorbisSample_residues;

    @ObfuscatedName("x")
    @ObfuscatedSignature(descriptor = "[Law;")
    @Export("VorbisSample_mappings")
    static VorbisMapping[] VorbisSample_mappings;

    @ObfuscatedName("p")
    @Export("VorbisSample_blockFlags")
    static boolean[] VorbisSample_blockFlags;

    @ObfuscatedName("n")
    @Export("VorbisSample_mapping")
    static int[] VorbisSample_mapping;

    @ObfuscatedName("m")
    static boolean field374;

    @ObfuscatedName("t")
    static float[] field394;

    @ObfuscatedName("k")
    static float[] field395;

    @ObfuscatedName("b")
    static float[] field396;

    @ObfuscatedName("z")
    static float[] field397;

    @ObfuscatedName("ap")
    static float[] field378;

    @ObfuscatedName("af")
    static float[] field372;

    @ObfuscatedName("ak")
    static float[] field400;

    @ObfuscatedName("av")
    static int[] field401;

    @ObfuscatedName("ar")
    static int[] field402;

    @ObfuscatedName("c")
    byte[][] field398;

    @ObfuscatedName("l")
    @Export("sampleRate")
    int sampleRate;

    @ObfuscatedName("s")
    @Export("sampleCount")
    int sampleCount;

    @ObfuscatedName("e")
    @Export("start")
    int start;

    @ObfuscatedName("r")
    @Export("end")
    int end;

    @ObfuscatedName("o")
    boolean field386;

    @ObfuscatedName("d")
    float[] field393;

    @ObfuscatedName("j")
    int field391;

    @ObfuscatedName("f")
    int field392;

    @ObfuscatedName("g")
    boolean field389;

    @ObfuscatedName("al")
    @Export("samples")
    byte[] samples;

    @ObfuscatedName("aa")
    int field404;

    @ObfuscatedName("ao")
    int field373;

    static {
        field374 = false;
    }

    VorbisSample(byte[] var1) {
        this.read(var1);
    }

    @ObfuscatedName("r")
    @Export("read")
    void read(byte[] var1) {
        Buffer var2 = new Buffer(var1);
        this.sampleRate = var2.readInt();
        this.sampleCount = var2.readInt();
        this.start = var2.readInt();
        this.end = var2.readInt();
        if (this.end < 0) {
            this.end = ~this.end;
            this.field386 = true;
        }
        int var3 = var2.readInt();
        this.field398 = new byte[var3][];
        for (int var4 = 0; var4 < var3; ++var4) {
            int var5 = 0;
            int var6;
            do {
                var6 = var2.readUnsignedByte();
                var5 += var6;
            } while (var6 >= 255 );
            byte[] var7 = new byte[var5];
            var2.readBytes(var7, 0, var5);
            this.field398[var4] = var7;
        }
    }

    @ObfuscatedName("i")
    float[] method1007(int var1) {
        VorbisSample_setData(this.field398[var1], 0);
        readBit();
        int var2 = readBits(class111.iLog(VorbisSample.VorbisSample_mapping.length - 1));
        boolean var3 = VorbisSample_blockFlags[var2];
        int var4 = (var3) ? VorbisSample_blockSize1 : VorbisSample_blockSize0;
        boolean var5 = false;
        boolean var6 = false;
        if (var3) {
            var5 = readBit() != 0;
            var6 = readBit() != 0;
        }
        int var7 = var4 >> 1;
        int var8;
        int var9;
        int var10;
        if (var3 && (!var5)) {
            var8 = (var4 >> 2) - (VorbisSample_blockSize0 >> 2);
            var9 = (VorbisSample_blockSize0 >> 2) + (var4 >> 2);
            var10 = VorbisSample_blockSize0 >> 1;
        } else {
            var8 = 0;
            var9 = var7;
            var10 = var4 >> 1;
        }
        int var11;
        int var12;
        int var13;
        if (var3 && (!var6)) {
            var11 = (var4 - (var4 >> 2)) - (VorbisSample_blockSize0 >> 2);
            var12 = (VorbisSample_blockSize0 >> 2) + (var4 - (var4 >> 2));
            var13 = VorbisSample_blockSize0 >> 1;
        } else {
            var11 = var7;
            var12 = var4;
            var13 = var4 >> 1;
        }
        VorbisMapping var14 = VorbisSample_mappings[VorbisSample_mapping[var2]];
        int var16 = var14.mappingMux;
        int var17 = var14.submapFloor[var16];
        boolean var15 = !VorbisSample_floors[var17].readSubmapFloor();
        boolean var45 = var15;
        for (var17 = 0; var17 < var14.submaps; ++var17) {
            VorbisResidue var42 = VorbisSample_residues[var14.submapResidue[var17]];
            float[] var44 = field394;
            var42.method801(var44, var4 >> 1, var45);
        }
        int var18;
        if (!var15) {
            var17 = var14.mappingMux;
            var18 = var14.submapFloor[var17];
            VorbisSample_floors[var18].method706(field394, var4 >> 1);
        }
        int var19;
        if (var15) {
            for (var17 = var4 >> 1; var17 < var4; ++var17) {
                field394[var17] = 0.0F;
            }
        } else {
            var17 = var4 >> 1;
            var18 = var4 >> 2;
            var19 = var4 >> 3;
            float[] var20 = field394;
            int var21;
            for (var21 = 0; var21 < var17; ++var21) {
                var20[var21] *= 0.5F;
            }
            for (var21 = var17; var21 < var4; ++var21) {
                var20[var21] = -var20[(var4 - var21) - 1];
            }
            float[] var40 = (var3) ? field378 : field395;
            float[] var22 = (var3) ? field372 : field396;
            float[] var23 = (var3) ? field400 : field397;
            int[] var24 = (var3) ? field402 : field401;
            int var25;
            float var26;
            float var27;
            float var28;
            float var29;
            for (var25 = 0; var25 < var18; ++var25) {
                var26 = var20[var25 * 4] - var20[(var4 - (var25 * 4)) - 1];
                var27 = var20[(var25 * 4) + 2] - var20[(var4 - (var25 * 4)) - 3];
                var28 = var40[var25 * 2];
                var29 = var40[(var25 * 2) + 1];
                var20[(var4 - (var25 * 4)) - 1] = (var26 * var28) - (var27 * var29);
                var20[(var4 - (var25 * 4)) - 3] = (var26 * var29) + (var27 * var28);
            }
            float var30;
            float var31;
            for (var25 = 0; var25 < var19; ++var25) {
                var26 = var20[(var17 + (var25 * 4)) + 3];
                var27 = var20[(var17 + (var25 * 4)) + 1];
                var28 = var20[(var25 * 4) + 3];
                var29 = var20[(var25 * 4) + 1];
                var20[(var17 + (var25 * 4)) + 3] = var26 + var28;
                var20[(var17 + (var25 * 4)) + 1] = var27 + var29;
                var30 = var40[(var17 - 4) - (var25 * 4)];
                var31 = var40[(var17 - 3) - (var25 * 4)];
                var20[(var25 * 4) + 3] = ((var26 - var28) * var30) - ((var27 - var29) * var31);
                var20[(var25 * 4) + 1] = ((var27 - var29) * var30) + ((var26 - var28) * var31);
            }
            var25 = class111.iLog(var4 - 1);
            int var46;
            int var47;
            int var48;
            int var49;
            for (var46 = 0; var46 < (var25 - 3); ++var46) {
                var47 = var4 >> (var46 + 2);
                var48 = 8 << var46;
                for (var49 = 0; var49 < (2 << var46); ++var49) {
                    int var50 = var4 - ((var47 * var49) * 2);
                    int var51 = var4 - (var47 * ((var49 * 2) + 1));
                    for (int var32 = 0; var32 < (var4 >> (var46 + 4)); ++var32) {
                        int var33 = var32 * 4;
                        float var34 = var20[(var50 - 1) - var33];
                        float var35 = var20[(var50 - 3) - var33];
                        float var36 = var20[(var51 - 1) - var33];
                        float var37 = var20[(var51 - 3) - var33];
                        var20[(var50 - 1) - var33] = var34 + var36;
                        var20[(var50 - 3) - var33] = var35 + var37;
                        float var38 = var40[var32 * var48];
                        float var39 = var40[(var32 * var48) + 1];
                        var20[(var51 - 1) - var33] = ((var34 - var36) * var38) - ((var35 - var37) * var39);
                        var20[(var51 - 3) - var33] = ((var35 - var37) * var38) + ((var34 - var36) * var39);
                    }
                }
            }
            for (var46 = 1; var46 < (var19 - 1); ++var46) {
                var47 = var24[var46];
                if (var46 < var47) {
                    var48 = var46 * 8;
                    var49 = var47 * 8;
                    var30 = var20[var48 + 1];
                    var20[var48 + 1] = var20[var49 + 1];
                    var20[var49 + 1] = var30;
                    var30 = var20[var48 + 3];
                    var20[var48 + 3] = var20[var49 + 3];
                    var20[var49 + 3] = var30;
                    var30 = var20[var48 + 5];
                    var20[var48 + 5] = var20[var49 + 5];
                    var20[var49 + 5] = var30;
                    var30 = var20[var48 + 7];
                    var20[var48 + 7] = var20[var49 + 7];
                    var20[var49 + 7] = var30;
                }
            }
            for (var46 = 0; var46 < var17; ++var46) {
                var20[var46] = var20[(var46 * 2) + 1];
            }
            for (var46 = 0; var46 < var19; ++var46) {
                var20[(var4 - 1) - (var46 * 2)] = var20[var46 * 4];
                var20[(var4 - 2) - (var46 * 2)] = var20[(var46 * 4) + 1];
                var20[((var4 - var18) - 1) - (var46 * 2)] = var20[(var46 * 4) + 2];
                var20[((var4 - var18) - 2) - (var46 * 2)] = var20[(var46 * 4) + 3];
            }
            for (var46 = 0; var46 < var19; ++var46) {
                var27 = var23[var46 * 2];
                var28 = var23[(var46 * 2) + 1];
                var29 = var20[var17 + (var46 * 2)];
                var30 = var20[(var17 + (var46 * 2)) + 1];
                var31 = var20[(var4 - 2) - (var46 * 2)];
                float var52 = var20[(var4 - 1) - (var46 * 2)];
                float var53 = (var28 * (var29 - var31)) + (var27 * (var30 + var52));
                var20[var17 + (var46 * 2)] = ((var29 + var31) + var53) * 0.5F;
                var20[(var4 - 2) - (var46 * 2)] = ((var29 + var31) - var53) * 0.5F;
                var53 = (var28 * (var30 + var52)) - (var27 * (var29 - var31));
                var20[(var17 + (var46 * 2)) + 1] = ((var30 - var52) + var53) * 0.5F;
                var20[(var4 - 1) - (var46 * 2)] = (((-var30) + var52) + var53) * 0.5F;
            }
            for (var46 = 0; var46 < var18; ++var46) {
                var20[var46] = (var20[var17 + (var46 * 2)] * var22[var46 * 2]) + (var20[(var17 + (var46 * 2)) + 1] * var22[(var46 * 2) + 1]);
                var20[(var17 - 1) - var46] = (var20[var17 + (var46 * 2)] * var22[(var46 * 2) + 1]) - (var20[(var17 + (var46 * 2)) + 1] * var22[var46 * 2]);
            }
            for (var46 = 0; var46 < var18; ++var46) {
                var20[var46 + (var4 - var18)] = -var20[var46];
            }
            for (var46 = 0; var46 < var18; ++var46) {
                var20[var46] = var20[var18 + var46];
            }
            for (var46 = 0; var46 < var18; ++var46) {
                var20[var18 + var46] = -var20[(var18 - var46) - 1];
            }
            for (var46 = 0; var46 < var18; ++var46) {
                var20[var17 + var46] = var20[(var4 - var46) - 1];
            }
            float[] var10000;
            for (var46 = var8; var46 < var9; ++var46) {
                var27 = ((float) (Math.sin((((((double) (var46 - var8)) + 0.5) / ((double) (var10))) * 0.5) * 3.141592653589793)));
                var10000 = field394;
                var10000[var46] *= ((float) (Math.sin((1.5707963267948966 * ((double) (var27))) * ((double) (var27)))));
            }
            for (var46 = var11; var46 < var12; ++var46) {
                var27 = ((float) (Math.sin(((((((double) (var46 - var11)) + 0.5) / ((double) (var13))) * 0.5) * 3.141592653589793) + 1.5707963267948966)));
                var10000 = field394;
                var10000[var46] *= ((float) (Math.sin((1.5707963267948966 * ((double) (var27))) * ((double) (var27)))));
            }
        }
        float[] var41 = null;
        if (this.field391 > 0) {
            var18 = (var4 + this.field391) >> 2;
            var41 = new float[var18];
            int var43;
            if (!this.field389) {
                for (var19 = 0; var19 < this.field392; ++var19) {
                    var43 = var19 + (this.field391 >> 1);
                    var41[var19] += this.field393[var43];
                }
            }
            if (!var15) {
                for (var19 = var8; var19 < (var4 >> 1); ++var19) {
                    var43 = (var41.length - (var4 >> 1)) + var19;
                    var41[var43] += field394[var19];
                }
            }
        }
        float[] var54 = this.field393;
        this.field393 = field394;
        field394 = var54;
        this.field391 = var4;
        this.field392 = var12 - (var4 >> 1);
        this.field389 = var15;
        return var41;
    }

    @ObfuscatedName("a")
    @ObfuscatedSignature(descriptor = "([I)Lab;")
    @Export("toRawSound")
    RawSound toRawSound(int[] var1) {
        if ((var1 != null) && (var1[0] <= 0)) {
            return null;
        } else {
            if (this.samples == null) {
                this.field391 = 0;
                this.field393 = new float[VorbisSample_blockSize1];
                this.samples = new byte[this.sampleCount];
                this.field404 = 0;
                this.field373 = 0;
            }
            for (; this.field373 < this.field398.length; ++this.field373) {
                if ((var1 != null) && (var1[0] <= 0)) {
                    return null;
                }
                float[] var2 = this.method1007(this.field373);
                if (var2 != null) {
                    int var3 = this.field404;
                    int var4 = var2.length;
                    if (var4 > (this.sampleCount - var3)) {
                        var4 = this.sampleCount - var3;
                    }
                    for (int var5 = 0; var5 < var4; ++var5) {
                        int var6 = ((int) (128.0F + (var2[var5] * 128.0F)));
                        if ((var6 & (-256)) != 0) {
                            var6 = (~var6) >> 31;
                        }
                        this.samples[var3++] = ((byte) (var6 - 128));
                    }
                    if (var1 != null) {
                        var1[0] -= var3 - this.field404;
                    }
                    this.field404 = var3;
                }
            }
            this.field393 = null;
            byte[] var7 = this.samples;
            this.samples = null;
            return new RawSound(this.sampleRate, var7, this.start, this.end, this.field386);
        }
    }

    @ObfuscatedName("c")
    @Export("float32Unpack")
    static float float32Unpack(int var0) {
        int var1 = var0 & 2097151;
        int var2 = var0 & Integer.MIN_VALUE;
        int var3 = (var0 & 2145386496) >> 21;
        if (var2 != 0) {
            var1 = -var1;
        }
        return ((float) (((double) (var1)) * Math.pow(2.0, ((double) (var3 - 788)))));
    }

    @ObfuscatedName("l")
    @Export("VorbisSample_setData")
    static void VorbisSample_setData(byte[] var0, int var1) {
        VorbisSample_bytes = var0;
        VorbisSample_byteOffset = var1;
        VorbisSample_bitOffset = 0;
    }

    @ObfuscatedName("s")
    @Export("readBit")
    static int readBit() {
        int var0 = (VorbisSample_bytes[VorbisSample_byteOffset] >> VorbisSample_bitOffset) & 1;
        ++VorbisSample_bitOffset;
        VorbisSample_byteOffset += VorbisSample_bitOffset >> 3;
        VorbisSample_bitOffset &= 7;
        return var0;
    }

    @ObfuscatedName("e")
    @Export("readBits")
    static int readBits(int var0) {
        int var1 = 0;
        int var2;
        int var3;
        for (var2 = 0; var0 >= (8 - VorbisSample_bitOffset); var0 -= var3) {
            var3 = 8 - VorbisSample_bitOffset;
            int var4 = (1 << var3) - 1;
            var1 += ((VorbisSample_bytes[VorbisSample_byteOffset] >> VorbisSample_bitOffset) & var4) << var2;
            VorbisSample_bitOffset = 0;
            ++VorbisSample_byteOffset;
            var2 += var3;
        }
        if (var0 > 0) {
            var3 = (1 << var0) - 1;
            var1 += ((VorbisSample_bytes[VorbisSample_byteOffset] >> VorbisSample_bitOffset) & var3) << var2;
            VorbisSample_bitOffset += var0;
        }
        return var1;
    }

    @ObfuscatedName("o")
    static void method1005(byte[] var0) {
        VorbisSample_setData(var0, 0);
        VorbisSample_blockSize0 = 1 << readBits(4);
        VorbisSample_blockSize1 = 1 << readBits(4);
        field394 = new float[VorbisSample_blockSize1];
        int var1;
        int var2;
        int var3;
        int var4;
        int var5;
        for (var1 = 0; var1 < 2; ++var1) {
            var2 = (var1 != 0) ? VorbisSample_blockSize1 : VorbisSample_blockSize0;
            var3 = var2 >> 1;
            var4 = var2 >> 2;
            var5 = var2 >> 3;
            float[] var12 = new float[var3];
            for (int var7 = 0; var7 < var4; ++var7) {
                var12[var7 * 2] = ((float) (Math.cos((((double) (var7 * 4)) * 3.141592653589793) / ((double) (var2)))));
                var12[(var7 * 2) + 1] = -((float) (Math.sin((((double) (var7 * 4)) * 3.141592653589793) / ((double) (var2)))));
            }
            float[] var13 = new float[var3];
            for (int var8 = 0; var8 < var4; ++var8) {
                var13[var8 * 2] = ((float) (Math.cos((((double) ((var8 * 2) + 1)) * 3.141592653589793) / ((double) (var2 * 2)))));
                var13[(var8 * 2) + 1] = ((float) (Math.sin((((double) ((var8 * 2) + 1)) * 3.141592653589793) / ((double) (var2 * 2)))));
            }
            float[] var14 = new float[var4];
            for (int var9 = 0; var9 < var5; ++var9) {
                var14[var9 * 2] = ((float) (Math.cos((((double) ((var9 * 4) + 2)) * 3.141592653589793) / ((double) (var2)))));
                var14[(var9 * 2) + 1] = -((float) (Math.sin((((double) ((var9 * 4) + 2)) * 3.141592653589793) / ((double) (var2)))));
            }
            int[] var15 = new int[var5];
            int var10 = class111.iLog(var5 - 1);
            for (int var11 = 0; var11 < var5; ++var11) {
                var15[var11] = ReflectionCheck.method630(var11, var10);
            }
            if (var1 != 0) {
                field378 = var12;
                field372 = var13;
                field400 = var14;
                field402 = var15;
            } else {
                field395 = var12;
                field396 = var13;
                field397 = var14;
                field401 = var15;
            }
        }
        var1 = readBits(8) + 1;
        VorbisSample_codebooks = new VorbisCodebook[var1];
        for (var2 = 0; var2 < var1; ++var2) {
            VorbisSample_codebooks[var2] = new VorbisCodebook();
        }
        var2 = readBits(6) + 1;
        for (var3 = 0; var3 < var2; ++var3) {
            readBits(16);
        }
        var2 = readBits(6) + 1;
        VorbisSample_floors = new VorbisFloor[var2];
        for (var3 = 0; var3 < var2; ++var3) {
            VorbisSample_floors[var3] = new VorbisFloor();
        }
        var3 = readBits(6) + 1;
        VorbisSample_residues = new VorbisResidue[var3];
        for (var4 = 0; var4 < var3; ++var4) {
            VorbisSample_residues[var4] = new VorbisResidue();
        }
        var4 = readBits(6) + 1;
        VorbisSample_mappings = new VorbisMapping[var4];
        for (var5 = 0; var5 < var4; ++var5) {
            VorbisSample_mappings[var5] = new VorbisMapping();
        }
        var5 = readBits(6) + 1;
        VorbisSample_blockFlags = new boolean[var5];
        VorbisSample_mapping = new int[var5];
        for (int var6 = 0; var6 < var5; ++var6) {
            VorbisSample_blockFlags[var6] = readBit() != 0;
            readBits(16);
            readBits(16);
            VorbisSample_mapping[var6] = readBits(8);
        }
    }

    @ObfuscatedName("w")
    @ObfuscatedSignature(descriptor = "(Lkq;)Z")
    static boolean method1028(AbstractArchive var0) {
        if (!field374) {
            byte[] var1 = var0.takeFile(0, 0);
            if (var1 == null) {
                return false;
            }
            method1005(var1);
            field374 = true;
        }
        return true;
    }

    @ObfuscatedName("v")
    @ObfuscatedSignature(descriptor = "(Lkq;II)Lah;")
    @Export("readMusicSample")
    static VorbisSample readMusicSample(AbstractArchive var0, int var1, int var2) {
        if (!method1028(var0)) {
            var0.tryLoadFile(var1, var2);
            return null;
        } else {
            byte[] var3 = var0.takeFile(var1, var2);
            return var3 == null ? null : new VorbisSample(var3);
        }
    }
}