package org.mulesoft.modules.aws.kms.api.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;


/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "aws-kms")
@Extension(name = "AWS-KMS")
@Configurations(AwskmsConfiguration.class)
@ErrorTypes(AwsKmsError.class)
public class AwskmsExtension {

}
