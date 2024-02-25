package madiCrypto;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DH {
  private BigInteger publicKey;
  private BigInteger privateKey;
  private BigInteger p;
  private BigInteger base;
  private BigInteger secretKey;

  public DH(BigInteger p, BigInteger base) {
    this.p = p;
    this.base = base;
    privateKey = new BigInteger(p.bitLength(), new SecureRandom());
    publicKey = base.modPow(privateKey, p);
  }

  public void calculateSecretKey(BigInteger publicKey) {
    secretKey = publicKey.modPow(privateKey, p);
  }

  public BigInteger getPublicKey() {
    return publicKey;
  }

  public BigInteger getPrivateKey() {
    return privateKey;
  }

  public BigInteger getPrimaryValue() {
    return p;
  }

  public BigInteger getBase() {
    return base;
  }

  public BigInteger getSecretKey() {
    return secretKey;
  }
}
