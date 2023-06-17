*** Settings ***
Library    SeleniumLibrary
Library    Collections

*** Variables ***
#Third Scenario
${sleepTime}    2s
${expected_type}    Action
${minRange}    2010
${maxRange}    2020

#First&Second Scenarios
${desired_movie}    Shawshank Redemption
${message}        we are here


*** Test Cases ***
Scenario1
    [Setup]    Open Browser    https://www.imdb.com/    chrome
    Input Text    id=suggestion-search    ${desired_movie}
    Click Button    id=suggestion-search-button
    ${movies_element} =    Get WebElements    class:ipc-metadata-list-summary-item__t
    ${Movies_names}    Create List
    FOR    ${movie_name}    IN    @{movies_element}
        ${Name}=    get text    ${movie_name}
        Should Contain Any    ${Name}    ${desired_movie}
        Append To List    ${Movies_names}    ${Name}
    END
    Should Be Equal As Strings    ${Movies_names}[0]    The Shawshank Redemption

    Sleep    ${sleepTime}
    [Teardown]    Close Browser

Scenario2
    [Setup]    Open Browser    https://www.imdb.com/    chrome
    Click Element    id=imdbHeader-navDrawerOpen
    Sleep    ${sleepTime}
    Click Element    xpath=//*[@id="imdbHeader"]/div[2]/aside/div/div[2]/div/div[1]/span/label/span[2]
    Sleep    ${sleepTime}
    Click Element    xpath=//*[@id="imdbHeader"]/div[2]/aside/div/div[2]/div/div[1]/span/div/div/ul/a[2]/span

    ${movies_element} =    Get WebElements    class:titleColumn
    ${Moves_names}    Create List
    FOR    ${move_name}    IN    @{movies_element}
        ${Name}=    get text    ${move_name}
        Append To List    ${Moves_names}    ${Name}
    END
    ${list_length}=    Get Length    ${Moves_names}
    Should Be Equal As Numbers    ${list_length}    250
    Should Be Equal As Strings    ${Moves_names}[0]    1. The Shawshank Redemption (1994)
    Should Contain Any    ${Moves_names}[0]    The Shawshank Redemption
    [Teardown]    Close Browser

Scenario3
    [Setup]    Open Browser    https://www.imdb.com/    chrome
    Maximize Browser Window
    Sleep    ${sleepTime}
    Click Element   xpath=//*[@id="nav-search-form"]/div[1]/div/label
    Sleep    ${sleepTime}
    Click Element    xpath=//*[@id="navbar-search-category-select-contents"]/ul/a/span[1]
    Sleep    ${sleepTime}
    Click Element    xpath=//*[@id="main"]/div[2]/div[1]/a
    Sleep    ${sleepTime}
    Select Checkbox    id=title_type-1
    Sleep    ${sleepTime}
    Select Checkbox    id=genres-1
    Sleep    ${sleepTime}
    Input Text    name=release_date-min    2010
    Sleep    ${sleepTime}
    Input Text    name=release_date-max    2020
    Sleep    ${sleepTime}
    Click Button    xpath=//*[@id="main"]/p[3]/button
    Sleep    ${sleepTime}
    Click Element    xpath=//*[@id="main"]/div/div[2]/a[3]
    Sleep    ${sleepTime}


    #Check Rates
    ${moviesRatesNumber}    Create List
    ${movies_rates}=    Get WebElements    class:ratings-imdb-rating
    FOR    ${movie_rate}    IN    @{movies_rates}
           ${rate}=    get text    ${movie_rate}
           ${rate}  Convert To Number    ${rate}
           Append To List    ${moviesRatesNumber}    ${rate}
    END
    @{sorted_list}=    Copy List    ${moviesRatesNumber}
    #Ascending
    Sort List    ${sorted_list}

    #Descending
    Reverse List    ${sorted_list}
    Lists Should Be Equal    ${sorted_list}    ${moviesRatesNumber}

    #Check Types
    ${movies_types}=    Get WebElements    class:genre
    Log List    ${movies_types}
    FOR    ${movie_type}    IN    @{movies_types}
           ${type}=    get text    ${movie_type}
           Should Contain Any    ${type}    ${expected_type}
    END

    #Check Years
    ${movies_year} =    Get WebElements    class:lister-item-year
    FOR    ${movie_year}    IN    @{movies_year}
           ${year}=    get text    ${movie_year}
           Log    ${year}
           ${year}=    Evaluate    "${year}".replace("(", "").replace(")", "").replace(" ", "").replace("I", "")
           ${year}    Convert To Integer    ${year}
           ${range}=  Evaluate  ${year} >= ${minRange} and ${year} <= ${maxRange}
           Should Be True      ${range}  # This should pass
    END

    [Teardown]    Close Browser





