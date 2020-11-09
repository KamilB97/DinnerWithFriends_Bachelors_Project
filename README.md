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

Shows available countries to choose 

### `/api/countries` 

Shows available cities to choose 

### `/api/cities`

Shows available provinces to choose 

### `/api/provinces`

Shows available interestings to choose 

### `/api/interestings`

Shows available dietary preferences to choose 

### `/api/dietarypreferences` 

</br>

## Profile

### `/api/friends/{profileID}`

### `/api/friends/delete/{first profileID}/{second profile ID}`

### `/api/invitations/{sender's profileId}/{receiver's profileID}`

### `/api/invitations/{profileID}`

### `/api/invitations/accept/{invitationID}`

### `/api/invitations/denial/{invitationID}`

Returns list of matched person
### `/api/matcher/matches/{profileID}`

Returns message list for conversation group
### `/api/messages/{conversationID}/{profileID}`

Returns myProfile all information
### `/api/profiles/myprofile/{profileID}`

Returns (not all) information about profile
### `/api/profiles/{profileID}`
`
Returns list with id of conversations with unreaded messages
### `/api/messages/unread/{profileID}`

Returns boolean value if user have unreaded messages for particular conversation
### `/api/messages/unread/{conversationID}/{profileID}`

Return list of conversation group members
### `/api/conversations/participants/{profileID}`

Delete particular member from conversation group
### `/api/conversations/participants/delete/{conversationID}/{profileID}`

Returns list of sugested (matched by interestings, dietary preferences and localization) people for particular profile
### `/api/matcher/candidates/{profileID}`

Update information about user choices at matching screen (used to upload user decision if he want to make an aquaintance with sugested person or not )
### `/api/matcher/updateswipe/{ProfileID with higher ID}/{ProfileID with lower ID}/{ProfileID of person who upload info}/{value 1- if not interested, 2- if interested}`

Returns list of conversation groups (made by user)
### `/api/conversations/custom/{profileID}`

Returns middle localization cords between conversation group participants and dominating culinary type
### `/api/conversations/participants/longlat/{conversationID}`

## POST Endpoints

### `/login`

### `/register`

Send message
### `/api/messages`

Check if inserted e-mail is available (account with that email do not exist in database)
### `/api/emails`

Update profile description
### `/api/profiles/about`

Upload/ update profile image
### `/api/images`

Create new conversation
#### `/api/conversations/custom`

## PUT Endpoints

Update profile information
#### `/api/profiles`

Change password
#### `/api/users/password`
