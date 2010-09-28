/*
 *  Copyright 2010 zinic.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package com.rackspace.cloud.sense.domain.entry;

import com.rackspace.cloud.sense.domain.AbstractRequest;
import org.apache.abdera.model.Entry;
import org.apache.abdera.protocol.server.RequestContext;

/**
 *
 * @author zinic
 */
public class SensePostEntryRequest extends AbstractRequest implements PostEntryRequest {

    private final Entry entryToPost;

    public SensePostEntryRequest(RequestContext requestContext, Entry entryToPost) {
        super(requestContext);

        this.entryToPost = entryToPost;
    }

    @Override
    public Entry getEntryToPost() {
        return entryToPost;
    }
}
