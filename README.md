# DinnerWithFriends_Bachelors_Project
Bachelor's project

</br>

## Table of contents

</br>

1. [ Features ](#features)
2. [ Technology stack ](#technologies)
3. [ Database schema ](#database)
4. [ Browser application images ](#browser)
5. [ Mobile application images ](#mobile)
6. [ API ](#api)

</br>

<a name="features"></a>
## Features

</br>


</br>

<a name="technologies"></a>
## Technology stack

### Server site
* Spring Boot
* Hibernate
* JWT
### Database
* MySQL
### Frontend
* JavaScript
* HTML
* CSS
* Google API (Maps JavaScript API and Places API)
### Mobile Application
* Android

</br>

<a name="database"></a>
# Database schema

</br>
<p align="center">
<img src="readme_images/database/db_schema.png" width="90%">
</P>
</br>

<a name="browser"></a>
# Browser application

</br>
</br>

## Login page 

</br>
<p align="center">
<img src="readme_images/browser/login.PNG" width="85%">
</P  
</br>

## Registration page 

</br>
<p align="center">
<img src="readme_images/browser/registration.PNG" width="85%">
</p>
</br>

## User profile 

</br>
<p align="center">
<img src="readme_images/browser/my_profile_edit.PNG" width="85%">
</p>
</br>

## User profile edit option

</br>
<p align="center">
<img src="readme_images/browser/my_profile_edit.PNG" width="85%" >
</p>
</br>

## Main board 

</br>
<p align="center">
<img src="readme_images/browser/main_board.PNG" width="85%">
</p>
</br>

## Accept friends 

</br>
<p align="center">
<img src="readme_images/browser/accept_friends.PNG" width="35%">
</p>
</br>

## Texting interpersonal 

</br>
<p align="center">
<img src="readme_images/browser/texting_interpersonal.PNG" width=85%">
</p>
</br>
                                                                    
## Texting within conversation group 

</br>
<p align="center">
<img src="readme_images/browser/texting_group.PNG" width="85%">
</p>
</br>

## Add to conversation group 

</br>
<p align="center">
<img src="readme_images/browser/add_to_group.PNG" width="85%">
</p>
</br>

## Find restaurant

</br>
<p align="center">
<img src="readme_images/browser/map_find_locals.PNG" width="85%">
</p>
</br>

## Find restaurant

</br>
<p align="center">
<img src="readme_images/browser/map_walk.PNG" width="85%">
</P>
</br>

## Matching 

</br>
<p align="center">
<img src="readme_images/browser/matching.PNG" width="85%">
</p>
</br>

<a name="mobile"></a>
# Mobile Application

</br>
</br>

## Login view 

</br>
<p align="center">
<img src="readme_images/mobile_app/login.png" width="30%">
</p>
</br>

## Main view 

</br>
<p align="center">
<img src="readme_images/mobile_app/menu.png" width="30%">
</p>
</br>

## My profile view

</br>
<p align="center">
<img src="readme_images/mobile_app/MyProfile.png" width="30%" hspace="5%"><img src="readme_images/mobile_app/MyProfile-description_after_swap_down.png" width="30%">
</p>
</br>

## Add friends view 

</br>
<p align="center">
<img src="readme_images/mobile_app/add_friends.PNG" width="30%">
</p>
</br>

## Matching view

</br>
<p align="center">
<img src="readme_images/mobile_app/Screenshot_20191205-155245.png" width="30%" hspace="3%"><img src="readme_images/mobile_app/Screenshot_20191205-155253.png" width="30%" hspace="3%"><img src="readme_images/mobile_app/matching_swap_annimation_v1.png" width="30%" hspace="3%">
</p>
</br>

## Friends list view 

</br>
<p align="center">
<img src="readme_images/mobile_app/Friends list.png" width="30%" hspace="5%"><img src="readme_images/mobile_app/Texting_interpersona.png" width="30%">
</p>
</br>

## User Profile view 

</br>
<p align="center">
<img src="readme_images/mobile_app/Someones_profile.png" width="30%" hspace="5%"><img src="readme_images/mobile_app/Someones_profile_description.png" width="30%">
</p>
</br>

## Conversations view 

</br>
<p align="center">
<img src="readme_images/mobile_app/My_Groups.png" width="30%" hspace="5%"><img src="readme_images/mobile_app/texting_within_group.png" width="30%">
</p>
</br>

## Matched people view 

</br>
<p align="center">
<img src="readme_images/mobile_app/chat_after_match.png" width="30%" hspace="5%"><img src="readme_images/mobile_app/matched_list.png" width="30%">
</p>
</br>
</br>

<a name="technologies"></a>
# API

Exported Postman API is available in Postman_api folder

</br>
## GET Endpoints

## Static information to show user

**Shows available countries to choose**

#### `/api/countries` 

**Shows available cities to choose**

#### `/api/cities`

**Shows available provinces to choose**

#### `/api/provinces`

**Shows available interestings to choose**

#### `/api/interestings`

**Shows available dietary preferences to choose**

#### `/api/dietarypreferences` 

</br>

## Profile

**Returns list of friends**
#### `/api/friends/{profileID}`

</br>
**Delete friend**
#### `/api/friends/delete/{first profileID}/{second profile ID}`

</br>
**Send friend invitation**
#### `/api/invitations/{sender's profileId}/{receiver's profileID}`

</br>
**Return list of invitation sent to profile**
#### `/api/invitations/{profileID}`

</br>
**Accept friend invitation**
#### `/api/invitations/accept/{invitationID}`

</br>
**Denial friend invitation**
#### `/api/invitations/denial/{invitationID}`

</br>
**Returns list of matched person**
#### `/api/matcher/matches/{profileID}`

</br>
**Returns message list for conversation group**
#### `/api/messages/{conversationID}/{profileID}`

</br>
**Returns myProfile all information**
#### `/api/profiles/myprofile/{profileID}`

</br>
**Returns (not all) information about profile**
#### `/api/profiles/{profileID}`

`</br>
**Returns list with id of conversations with unreaded messages**
#### `/api/messages/unread/{profileID}`

</br>
**Returns boolean value if user have unreaded messages for particular conversation**
#### `/api/messages/unread/{conversationID}/{profileID}`

</br>
**Return list of conversation group members**
#### `/api/conversations/participants/{profileID}`

</br>
**Delete particular member from conversation group**
#### `/api/conversations/participants/delete/{conversationID}/{profileID}`

</br>
**Returns list of sugested (matched by interestings, dietary preferences and localization) people for particular profile**
#### `/api/matcher/candidates/{profileID}`

</br>
**Update information about user choices at matching screen (used to upload user decision if he want to make an aquaintance with sugested person or not)**
#### `/api/matcher/updateswipe/{ProfileID with higher ID}/{ProfileID with lower ID}/{ProfileID of person who upload info}/{holds "1"- if not interested, "2"- if interested}`

</br>
**Returns list of conversation groups (made by user)**
#### `/api/conversations/custom/{profileID}`

</br>
**Returns middle localization cords between conversation group participants and dominating culinary type**
#### `/api/conversations/participants/longlat/{conversationID}`

</br>

## POST Endpoints

**Login**
#### `/login`
Body example
#### {</br>
"username":"kamilbrzycki@gmail.com",</br>
"password":"kamil1997"</br>
}

**Register**
#### `/register`
Body example
#### {</br>

"email" : "kamilbrzycki@gmail.com",</br>
"phone" : "796112221",</br>
"password" : "kamil1997"</br>

}

** Send message**
#### `/api/messages`
Body example
#### { </br>
"profile": {</br>
            "profileId": 35,</br>
            "name": "Cherry",</br>
            "surname": "Johnson"</br>
        },</br>
        "conversationId": 33,</br>
        "messageInfo": {</br>
            "messageTxt": "Halko, test powiadomień",</br>
            "date": "2019-10-15 23:28:29"</br>
        }
}

**Check if inserted e-mail is available (account with that email do not exist in database)**
#### `/api/emails`
Body example
#### {</br>
	"email": "grcza@gmail.com"</br>
}

**Update profile description**
#### `/api/profiles/about`
Body example
#### {</br>
"profileId" : 32, </br>
"about" : " testing about note "</br>
}

**Upload/ update profile image**
#### `/api/images`
Body example
#### {</br>
	"profileId" : 5,</br>
	"imageUrl" : "lcxsdasfsddfsfsdfsadfbdfgdsfgdfsgasdfsreagwgsadfsdffsfjfhsdfsadfsdfsfsfsflcxsdasfsddfssdfsadfbdfgdsfgdfsgasdfsreagwgsadfsdffsfjfhsdfsadfsdfsfsfslcxsdasfsddfssdfsadfbdfgdsfgdfsgasdfsreagwgsadfsdffsfjfhsdfsadfsdfsfsfss"</br>
}

**Create new conversation**
#### `/api/conversations/custom`
Body example
#### {</br>
"customConversation" : {	</br>
"name" : "Not exactly what you think",</br>
"customCreated" : 1</br>
},</br>
"participants" : [</br>
	{</br>
	"profileId" : 10,</br>
	"name" : "Tyler",</br>
	"surname" : "Spencer"</br>
	},</br>
	{</br>
	"profileId" : 38,</br>
	"name" : "Kamil",</br>
	"surname" :  "Brzycki"</br>
	},</br>
	{</br>
	"profileId" : 15,</br>
	"name" : "Lana",</br>
	"surname" : "Myers"</br>
	}</br>
	
]</br>
}


</br>

## PUT Endpoints

**Update profile information**
#### `/api/profiles`
Body example
#### {</br>
"profileId":31,</br>
"userId" : 31,</br>
"firstName" : "Gosia",</br>
"lastName" : "Bączar",</br>
"gender" : "female",</br>
"age" : 22,</br>

"cityId" : 1,</br>
"about" : "FFull passion economist, likes football, voleyball, eager to meet other pasionats",</br>

"dietary" : [1,3,5],</br>

"interestings" : [1,4,5]</br>
}</br>

**Change password**
#### `/api/users/password`
Body example
#### {</br>
	"userId": 37,</br>
	"oldPassword": "oldPassword",</br>
	"newPassword": "newPassword"</br>
}
