/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jdbi.v3.core.internal.defaults.arguments;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.URI;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.Types;

// :D
public class InternetArgumentFactory extends DelegatingArgumentFactory {
    public InternetArgumentFactory() {
        register(Inet4Address.class, Types.OTHER, (p, i, v) -> p.setString(i, v.getHostAddress()));
        register(Inet6Address.class, Types.OTHER, (p, i, v) -> p.setString(i, v.getHostAddress()));
        register(URL.class, Types.DATALINK, PreparedStatement::setURL);
        register(URI.class, Types.VARCHAR, new ToStringBinder<>(PreparedStatement::setString));
    }
}
