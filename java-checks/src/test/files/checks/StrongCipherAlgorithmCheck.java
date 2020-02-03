import javax.crypto.Cipher;
import javax.crypto.NullCipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

class MyCipher extends Cipher {
  public MyCipher() {
    super(null, null, "");
  }
}

class A {
  void foo() throws NoSuchAlgorithmException, NoSuchPaddingException {
    Cipher c;
    c = Cipher.getInstance("DESede/ECB/PKCS5Padding"); // Noncompliant [[sc=28;ec=53]] {{Use a strong cipher algorithm.}}
    c = Cipher.getInstance("DES/ECB/PKCS5Padding");// Noncompliant
    c = Cipher.getInstance("RC2/ECB/PKCS5Padding"); // Noncompliant
    c = Cipher.getInstance("AES/GCM/NoPadding");//Compliant
    c = new NullCipher(); // Noncompliant [[sc=13;ec=23]] {{Use a strong cipher algorithm.}}
    c = new javax.crypto.NullCipher(); // Noncompliant
    c = new MyCipher();

    // DES
    c = Cipher.getInstance("DES"); // Noncompliant
    c = Cipher.getInstance("DES/ECB"); // Noncompliant
    c = Cipher.getInstance("DES/ECB/PKCS5Padding"); // Noncompliant
    c = Cipher.getInstance("DES/GCM"); // Noncompliant
    c = Cipher.getInstance("DES/GCM/NoPadding"); // Noncompliant
    c = Cipher.getInstance("DES/GCM/PKCS5Padding"); // Noncompliant

    // 3DES
    c = Cipher.getInstance("DESede"); // Noncompliant
    c = Cipher.getInstance("DESede/ECB"); // Noncompliant
    c = Cipher.getInstance("DESede/ECB/PKCS5Padding"); // Noncompliant
    c = Cipher.getInstance("DESede/GCM"); // Noncompliant
    c = Cipher.getInstance("DESede/GCM/NoPadding"); // Noncompliant
    c = Cipher.getInstance("DESede/GCM/PKCS5Padding"); // Noncompliant

    // RC2
    c = Cipher.getInstance("RC2"); // Noncompliant
    c = Cipher.getInstance("RC2/ECB"); // Noncompliant
    c = Cipher.getInstance("RC2/ECB/PKCS5Padding"); // Noncompliant
    c = Cipher.getInstance("RC2/GCM"); // Noncompliant
    c = Cipher.getInstance("RC2/GCM/NoPadding"); // Noncompliant
    c = Cipher.getInstance("RC2/GCM/PKCS5Padding"); // Noncompliant

    // RC4
    c = Cipher.getInstance("RC4"); // Noncompliant
    c = Cipher.getInstance("RC4/ECB"); // Noncompliant
    c = Cipher.getInstance("RC4/ECB/PKCS5Padding"); // Noncompliant
    c = Cipher.getInstance("RC4/GCM"); // Noncompliant
    c = Cipher.getInstance("RC4/GCM/NoPadding"); // Noncompliant
    c = Cipher.getInstance("RC4/GCM/PKCS5Padding"); // Noncompliant

    // Blowfish
    c = Cipher.getInstance("Blowfish"); // Noncompliant
    c = Cipher.getInstance("Blowfish/ECB"); // Noncompliant
    c = Cipher.getInstance("Blowfish/ECB/PKCS5Padding"); // Noncompliant
    c = Cipher.getInstance("Blowfish/GCM"); // Noncompliant
    c = Cipher.getInstance("Blowfish/GCM/NoPadding"); // Noncompliant
    c = Cipher.getInstance("Blowfish/GCM/PKCS5Padding"); // Noncompliant

    c = Cipher.getInstance("AES/GCM/NoPadding"); // Compliant

    c.getBlockSize();
  }

  void usingJavaUtilProperties(java.util.Properties props, String otherAlgo) throws NoSuchAlgorithmException, NoSuchPaddingException {
    String algo = props.getProperty("myAlgo", "DES/ECB/PKCS5Padding");
    Cipher c;
    c = Cipher.getInstance(algo); // Noncompliant
    c = Cipher.getInstance(props.getProperty("myAlgo", "DES/ECB/PKCS5Padding")); // Noncompliant
    c = Cipher.getInstance(getAlgo()); // Compliant
    c = Cipher.getInstance("/"); // Compliant

    String algo2 = props.getProperty("myAlgo");
    c = Cipher.getInstance(algo2); // Compliant

    String algo3 = props.getProperty("myAlgo", "DES/ECB/PKCS5Padding");
    algo3 = "myOtherAlgo";
    c = Cipher.getInstance(algo3); // Compliant

    String algo4 = getAlgo();
    c = Cipher.getInstance(algo4); // Compliant

    c = Cipher.getInstance(otherAlgo); // Compliant

    String algo5 = "myAlgo";
    c = Cipher.getInstance(algo5); // Compliant

    String algo6 = props.getProperty("myAlgo", getAlgo());
    c = Cipher.getInstance(algo6); // Compliant
    c.getBlockSize();
  }

  private String getAlgo() {
    return null;
  }
}
