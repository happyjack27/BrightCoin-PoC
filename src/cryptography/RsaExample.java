package cryptography;

import javax.crypto.Cipher;
import java.io.InputStream;
import java.security.*;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RsaExample {
	public static String CIPHER = "RSA";
	
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(CIPHER);
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }

    public static KeyPair getKeyPairFromKeyStore() throws Exception {
        //Generated with:
        //  keytool -genkeypair -alias mykey -storepass s3cr3t -keypass s3cr3t -keyalg RSA -keystore keystore.jks

        InputStream ins = RsaExample.class.getResourceAsStream("/keystore.jks");

        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        keyStore.load(ins, "s3cr3t".toCharArray());   //Keystore password
        KeyStore.PasswordProtection keyPassword =       //Key password
                new KeyStore.PasswordProtection("s3cr3t".toCharArray());

        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("mykey", keyPassword);

        java.security.cert.Certificate cert = keyStore.getCertificate("mykey");
        PublicKey publicKey = cert.getPublicKey();
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();

        return new KeyPair(publicKey, privateKey);
    }
    
    //base64
    public static String toBase64(byte[] s) {
    	return Base64.getEncoder().encodeToString(s);
    }
    public static byte[] fromBase64(String s) {
    	return Base64.getDecoder().decode(s);
    }
    
    
    //replication confirmation
    public static byte[] encrypt(byte[] plainText, PrivateKey privateKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance(CIPHER);
        encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return encryptCipher.doFinal(plainText);
    }

    public static byte[] decrypt(byte[] cipherText, PublicKey publicKey) throws Exception {
        Cipher decriptCipher = Cipher.getInstance(CIPHER);
        decriptCipher.init(Cipher.DECRYPT_MODE, publicKey);
        return decriptCipher.doFinal(cipherText);
    }

    //original
    public static byte[] encrypt(byte[] plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance(CIPHER);
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return encryptCipher.doFinal(plainText);
    }

    public static byte[] decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception {
        Cipher decriptCipher = Cipher.getInstance(CIPHER);
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return decriptCipher.doFinal(cipherText);
    }

    public static String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }

    public static void main(String... argv) throws Exception {
        //First generate a public/private key pair
        KeyPair pair = generateKeyPair();
        //KeyPair pair = getKeyPairFromKeyStore();

        //Our secret message
        String message = "the answer to life the universe and everything";
        System.out.println("private: "+toBase64(pair.getPrivate().getEncoded()));
        System.out.println("public: "+toBase64(pair.getPublic().getEncoded()));

        byte[] cipherText = encrypt(message.getBytes(), pair.getPublic());
        String decipheredMessage = new String(decrypt(cipherText, pair.getPrivate()));
        System.out.println(decipheredMessage);

        //reversed.
        byte[] cipherText2 = encrypt(message.getBytes(), pair.getPrivate());
        String decipheredMessage2 = new String(decrypt(cipherText2, pair.getPublic()));
        System.out.println(decipheredMessage2);

        //Let's sign our message
        String signature = sign("foobar", pair.getPrivate());

        //Let's check the signature
        boolean isCorrect = verify("foobar", signature, pair.getPublic());
        System.out.println("Signature correct: " + isCorrect);
    }
}