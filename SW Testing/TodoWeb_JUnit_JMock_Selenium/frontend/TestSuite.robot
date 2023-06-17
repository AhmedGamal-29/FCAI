*** Settings ***
Library    SeleniumLibrary
Library    Collections

*** Variables ***
${sleepTime}    2s
${url}    file:///home/ahmed/PycharmProjects/front_end/Software-Testing-Assi3-Frontend/todo.html


*** Keywords ***
Add Todo
    [Arguments]    ${title}    ${description}
    Input Text    id=todo    ${title}
    Input Text    id=desc    ${description}
    Sleep    ${sleepTime}
    Click Button   xpath=/html/body/div/div/div[1]/form/button

Get Table Row Count
    [Arguments]    ${table_locator}
    ${rows}=    Get WebElements    xpath://table[contains(@class, 'm-4 table table-striped')]//tr
    ${row_count}=    Get Length    ${rows}
    [Return]    ${row_count}

Count Rows
    [Arguments]    ${tbody_id}
    Wait Until Element Is Visible    id=${tbody_id}
    ${tr_elements}=    Get WebElements    xpath=//tbody[@id='${tbody_id}']//tr
    ${tr_count}=    Get Length    ${tr_elements}
    ${tr_count}    Convert To Integer    ${tr_count}
    [Return]    ${tr_count}




*** Test Cases ***
addTodo
    [Setup]    Open Browser    ${url}    chrome
    Maximize Browser Window
    Sleep    ${sleepTime}

    #help me to be sure that table appear
    Add Todo    "initialize_todo"    "Desc_init"

    #Number of todos before adding
    ${tr_count_before}=    Count Rows    todo-table

    #Add task
    Add Todo    "TODO_Added"    "Desc_Added"

    #text inputs become empty
    ${input1}=      Get Text    id=todo
    ${input2}=      Get Text    id=desc
    Should Be Empty    ${input1}    msg=Enter Todo
    Should Be Empty    ${input2}    msg=Description
    Sleep    ${sleepTime}

    #Check number of todos after adding
    ${tr_count_after}=    Count Rows    todo-table

    #Expected Count
    ${tr_count_before}     Evaluate    ${tr_count_before}+1

    Should Be Equal As Integers    ${tr_count_before}  ${tr_count_after}
    Sleep    ${sleepTime}

#    [Teardown]    Close Browser

updateTodo
#    [Setup]    Open Browser    ${url}    chrome
#    Maximize Browser Window
    Sleep    ${sleepTime}

    Select Checkbox    id=checkbox-1
    Sleep    ${sleepTime}

    #Test checkbox
    Checkbox Should Be Selected    id=checkbox-1
    Sleep    ${sleepTime}

#    [Teardown]    Close Browser

deleteTodo
#    [Setup]    Open Browser    ${url}    chrome
#    Maximize Browser Window

    #help me to be sure that table at least has one task
    Add Todo    "test_todo"    "Desc_test"

    #Number of todos before delete
    ${tr_count_before}=    Count Rows    todo-table

    Sleep    ${sleepTime}
    Click Button   xpath=/html/body/div/table/tbody/tr[1]/td[5]/button
    Sleep    ${sleepTime}

    #Number of todos after delete
    ${tr_count_after}=    Count Rows    todo-table

    #Expected
    ${tr_count_before}=     Evaluate    ${tr_count_before}-1

   #todos number decrease one
    Should Be Equal As Integers    ${tr_count_before}   ${tr_count_after}
#    [Teardown]    Close Browser

listTodo
#    [Setup]    Open Browser    ${url}    chrome
#    Maximize Browser Window

    #help me to be sure that table appear
    Add Todo    "initialize_todo"    "Desc_init"

    ${count_bef}=       Count Rows    todo-table


    Add Todo    "TODO1"    "DESC1"
    Add Todo    "TODO2"    "DESC2"
    Add Todo    "TODO3"    "DESC3"
    Add Todo    "TODO1"    "DESC4"

    #Expected before added by 4 tasks
    Click Button    xpath=/html/body/div/div/div[2]/button[1]
    ${count_bef}=    Evaluate    ${count_bef}+4

    #Test ListAll
    ${count_after_all}=       Count Rows    todo-table
    Should Be Equal As Integers    ${count_bef}     ${count_after_all}

    Select Checkbox    id=checkbox-2
    Sleep    ${sleepTime}

    Select Checkbox    id=checkbox-4
    Sleep    ${sleepTime}

    Select Checkbox    id=checkbox-5
    Sleep    ${sleepTime}

    #Test ListCompleted
    Click Button   xpath=/html/body/div/div/div[2]/button[2]
    ${count_after_completed}=       Count Rows    todo-table
    Should Be Equal As Integers    ${count_after_completed}     3
    [Teardown]    Close Browser
    
