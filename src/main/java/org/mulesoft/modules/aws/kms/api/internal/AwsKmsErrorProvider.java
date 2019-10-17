package org.mulesoft.modules.aws.kms.api.internal;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.HashSet;
import java.util.Set;

public class AwsKmsErrorProvider
    implements ErrorTypeProvider {
        @Override
        public Set<ErrorTypeDefinition> getErrorTypes() {
            HashSet<ErrorTypeDefinition> errors = new HashSet<>();
            errors.add(AwsKmsError.AWS_ERROR);
            return errors;
        }
}
