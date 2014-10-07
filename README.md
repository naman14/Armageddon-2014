Armageddon-2014
===============

App for the Annual Tech Week of IET DTU.

The official app of Armageddon 2014 gives you a glimpse of the events to be organized in the tech week along with coordinators for events and their contact details.

Armageddon 2014 app also enables push notifications so you can get important notices in your notifications.

Push Notifications uses Google Cloud Messaging and a open source server as a cloud endpoint.
Server is located at http://gcm4public.appspot.com/

Using this server is not advisable as it is hosted on Google App Engine and thus has its limitations and should not be
used if user base is in thousands.

Do remeber that GCMBaseIntentService is deprecated so you should use GoogleCloudMessaging API instead


License

Copyright (C) 2014 Naman Dwivedi  (namandwivedi14@gmail.com)

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.


