# Step One- Fill out the CAPSTONE_REPO_NAME and GITHUB_GROUP_NAME in all LOWERCASE.

# Step Two - configure your shell to always have these variables.
# For OSX / Linux
# Copy and paste ALL of the properties below into your .bash_profile in your home directly

# For Windows
# Copy and paste ALL of the properties below into your .bashrc file in your home directory

# In IntelliJ Terminal
# Type source ./setupEnvironment.sh

# Confirm set up by using echo $CAPSTONE_REPO_NAME and it should return what you typed in.

# Fill out the following values
# The path of your repo on github. Don't include the whole URL, just the part after github.com/KenzieAcademy-SoftwareEngineering/
<<<<<<< HEAD
<<<<<<< HEAD
export LBC_GROUP_NAME=teampearl
<<<<<<< HEAD
export LBC_REPO_NAME=ata-lbc-project-$bantoninese83

export GITHUB_USERNAME=emotheatrix
export LBC_GROUP_NAME=teamPearl
export LBC_REPO_NAME=ata-lbc-project-$GITHUB_USERNAME
=======
export LBC_REPO_NAME=ata-lbc-project-bantoinese83
>>>>>>> c82455f (added the sales and productList double check make sure everything is correct)

export LBC_GROUP_NAME=teampearl
export LBC_REPO_NAME=ata-lbc-project-$bantoninese83

=======

# Fill out the following values
# The path of your repo on github.  Don't but the whole URL, just the part after github.com/
=======
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")
export LBC_GROUP_NAME=teampearl
export LBC_REPO_NAME=ata-lbc-project-bantoinese83
=======

export LBC_GROUP_NAME=teampearl
export LBC_REPO_NAME=ata-lbc-project-$bantoninese83

<<<<<<< HEAD
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
=======
export GITHUB_USERNAME=emotheatrix
export LBC_GROUP_NAME=teamPearl
export LBC_REPO_NAME=ata-lbc-project-$GITHUB_USERNAME
>>>>>>> origin
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")

# Do not modify the rest of these unless you have been instructed to do so.
export LBC_PROJECT_NAME=lbcproject
export LBC_PIPELINE_STACK=$LBC_PROJECT_NAME-$LBC_GROUP_NAME
export LBC_ARTIFACT_BUCKET=$LBC_PROJECT_NAME-$LBC_GROUP_NAME-artifacts
export LBC_DEPLOY_STACK=$LBC_PROJECT_NAME-$LBC_GROUP_NAME-application
export LBC_APPLICATION_NAME=$LBC_PROJECT_NAME-$LBC_GROUP_NAME-application
export LBC_ENVIRONMENT_NAME=$LBC_PROJECT_NAME-$LBC_GROUP_NAME-environment-dev
