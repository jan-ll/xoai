/**
 * Copyright 2012 Lyncode
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

package com.lyncode.xoai.dataprovider.transform;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.lyncode.xoai.dataprovider.data.MetadataTransformer;
import com.lyncode.xoai.dataprovider.exceptions.ConfigurationException;
import com.lyncode.xoai.dataprovider.xml.xoaiconfig.Configuration.Transformers;
import com.lyncode.xoai.dataprovider.xml.xoaiconfig.Configuration.Transformers.Transformer;

/**
 * @author Development @ Lyncode <development@lyncode.com>
 * @version 2.2.9
 */
public class TransformManager {
	// private static Logger log = LogManager.getLogger(TransformManager.class);
	private Map<String, MetadataTransformer> _contexts;

	public TransformManager(String baseDir, Transformers config)
			throws ConfigurationException {
		_contexts = new HashMap<String, MetadataTransformer>();
		if (config != null) {
			for (Transformer t : config.getTransformer()) {
				String xsltFilepath = (baseDir.endsWith(File.separator) ? baseDir
						: baseDir + File.separator) + t.getXSLT();
				File xsltFile = new File(xsltFilepath);
				_contexts.put(t.getId(), new MetadataTransformer(xsltFile));
			}
		}
	}

	public boolean transformerExists(String id) {
		return this._contexts.containsKey(id);
	}

	public MetadataTransformer getTransformer(String id) {
		return _contexts.get(id);
	}

}
