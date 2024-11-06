package iticbcn.xifratge;

public class TextXifrat {
    
    public byte[] bytes = null;

    @Override
    public String toString() {
        return new String(bytes);
    }

    public TextXifrat(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

}
