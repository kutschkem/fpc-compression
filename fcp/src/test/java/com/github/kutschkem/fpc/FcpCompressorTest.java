package com.github.kutschkem.fpc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.nio.ByteBuffer;

import org.junit.Test;

public class FcpCompressorTest {

    @Test
    public void testLongToByteArray() {
        FpcCompressor comp = new FpcCompressor();
        for (long i = 0; i < 10000; i++) {
            assertEquals(i, comp.toLong(comp.toByteArray(i)));
        }
    }

    @Test
    public void testRoundtripWithTwoValues() {
        double[] doubles = new double[] { 1.0, 0.0 };
        FpcCompressor fpc = new FpcCompressor();

        ByteBuffer buffer = ByteBuffer.allocate(64);
        fpc.compress(buffer, doubles);

        buffer.flip();

        FpcCompressor decompressor = new FpcCompressor();

        double[] dest = new double[2];
        decompressor.decompress(buffer, dest);

        assertThat(dest, is(doubles));
    }

    @Test
    public void testRoundtripWithThreeValues() {
        double[] doubles = new double[] { 1.0, 0.0, 0.5 };
        FpcCompressor fpc = new FpcCompressor();

        ByteBuffer buffer = ByteBuffer.allocate(64);
        fpc.compress(buffer, doubles);

        buffer.flip();

        FpcCompressor decompressor = new FpcCompressor();

        double[] dest = new double[3];
        decompressor.decompress(buffer, dest);

        assertThat(dest, is(doubles));
    }

}
