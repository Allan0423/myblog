package info.enjoycoding.myblog.util;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

public class CryptoUtil {

    private static final String SALT = "myblog";

    public static String getDigest(String message){

        byte[] saltBytes = SALT.getBytes();
        byte[] msgBytes = message.getBytes();

        Digest digest = DigestFactory.createSHA256();
        byte[] out = new byte[digest.getDigestSize()];
        digest.reset();
        digest.update(saltBytes, 0, saltBytes.length);
        digest.update(msgBytes, 0, msgBytes.length);
        digest.doFinal(out, 0);
        
        return Base64.toBase64String(out);
    }

    public static void main(String[] args) {
        System.out.println(getDigest("123456"));
    }
}
