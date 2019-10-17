package org.mulesoft.modules.aws.kms.api.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.services.kms.model.EncryptRequest;
import com.amazonaws.util.Base64;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Password;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class AwskmsOperations {

  private static final Logger logger = LoggerFactory.getLogger(AwskmsOperations.class);

  /**
   * Operation to encrypt the plaintext using cms key and return output in base64encoded string
   *
   */
  @MediaType(value = ANY, strict = false)
  @DisplayName("Decrypt")
  @Summary("Decrypt the encrypted base64 string using CMS key in AWS KMS")
  public String decrypt(@Config AwskmsConfiguration configuration, String encryptedBase64String ) {

    try {

      byte[] cipherBytes = Base64.decode(encryptedBase64String);
      ByteBuffer cipherTextBlob = ByteBuffer.wrap(cipherBytes);
      DecryptRequest decryptRequest = new DecryptRequest().withCiphertextBlob(cipherTextBlob);
      DecryptResult result = configuration.getClient().decrypt(decryptRequest);
      ByteBuffer plainTextBuffer = result.getPlaintext();
      String decryptValue = new String(plainTextBuffer.array(), Charset.forName("UTF-8"));
      return decryptValue;
    }catch (Throwable e){
      logger.error("Failed to invoke AWS CMS decryption " + e.getMessage().toString());
      throw new ModuleException(AwsKmsError.AWS_ERROR, e);
    }
  }

  /**
   * Operation to encrypt the plaintext using cms key and return output in base64encoded string
   *
   */
  @MediaType(value = ANY, strict = false)
  @DisplayName("Encrypt")
  @Summary("Encrypt the plain text string using CMS key in AWS KMS")
  public String encrypt(@Config AwskmsConfiguration configuration, @Password String awsARNKeyId, String plainText ) {

    try {

      ByteBuffer plaintext = ByteBuffer.wrap(plainText.getBytes());
      EncryptRequest encryptRequest = new EncryptRequest().withKeyId(awsARNKeyId).withPlaintext(plaintext);

      ByteBuffer cipherText = configuration.getClient().encrypt(encryptRequest).getCiphertextBlob();
      byte[] base64EncodedValue = Base64.encode(cipherText.array());
      String value = new String(base64EncodedValue);
      return value;
    }catch (Throwable e){
      logger.error("Failed to invoke AWS CMS encryption " + e.getMessage().toString());
      throw new ModuleException(AwsKmsError.AWS_ERROR, e);
    }
  }

}
