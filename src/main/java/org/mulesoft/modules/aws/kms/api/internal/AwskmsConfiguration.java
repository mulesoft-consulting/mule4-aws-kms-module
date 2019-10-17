package org.mulesoft.modules.aws.kms.api.internal;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Password;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.annotation.values.OfValues;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(AwskmsOperations.class)
public class AwskmsConfiguration {

  AWSKMS client;

  @Parameter
  @Summary("AWS KMS key region as us-east-2")
  @OfValues(RegionsEnum.class)
  String region;


  @Parameter
  @Password
  @Summary("AWS Access Key")
  String awsAccessKey;

  @Parameter
  @Password
  @Summary("AWS Secret Key")
  String awsSecretKey;

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }


  public String getAwsAccessKey() {
    return awsAccessKey;
  }

  public void setAwsAccessKey(String awsAccessKey) {
    this.awsAccessKey = awsAccessKey;
  }

  public String getAwsSecretKey() {
    return awsSecretKey;
  }

  public void setAwsSecretKey(String awsSecretKey) {
    this.awsSecretKey = awsSecretKey;
  }

  public synchronized AWSKMS getClient() {
    if( client == null ) {
      AWSKMSClientBuilder clientBuilder = AWSKMSClientBuilder.standard().withRegion(region);
      BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
      client = clientBuilder.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

    }
    return client;
  }

  public synchronized void setClient(AWSKMS client) {
    this.client = client;
  }


}
