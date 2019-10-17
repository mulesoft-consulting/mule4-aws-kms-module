package org.mulesoft.modules.aws.kms.api.internal;

import org.mule.runtime.api.value.Value;
import org.mule.runtime.extension.api.values.ValueBuilder;
import org.mule.runtime.extension.api.values.ValueProvider;

import java.util.Set;

public class RegionsEnum implements ValueProvider {

    @Override
    public Set<Value> resolve() {
        return ValueBuilder.getValuesFor(
                "ap-south-1",
                "ap-northeast-2",
                "ap-southeast-1",
                "ap-southeast-2",
                "ap-northeast-1",
                "ca-central-1",
                "eu-central-1",
                "eu-west-1",
                "eu-west-2",
                "eu-west-3",
                "sa-east-1",
                "us-west-1",
                "us-west-2",
                "us-east-2",
                "us-east-1");
    }
}