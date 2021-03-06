/*
 * Copyright 2013-2014 Classmethod, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.classmethod.aws.gradle.ec2

import com.amazonaws.AmazonClientException
import com.amazonaws.services.ec2.AmazonEC2
import com.amazonaws.services.ec2.model.StartInstancesRequest
import java.util.List;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;


class AmazonEC2StartInstanceTask extends DefaultTask {
	
	{
		description 'Start EC2 instance.'
		group = 'AWS'
	}
	
	List<String> instanceIds = []
	
	@TaskAction
	def createApplication() {
		// to enable conventionMappings feature
		List<String> instanceIds = getInstanceIds()

		if (instanceIds.isEmpty()) return
		
		AmazonEC2PluginExtension ext = project.extensions.getByType(AmazonEC2PluginExtension)
		AmazonEC2 ec2 = ext.ec2
		
		ec2.startInstances(new StartInstancesRequest(instanceIds))
		logger.info "instance $instanceIds start requested"
	}
}
