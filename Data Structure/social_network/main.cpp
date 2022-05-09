#include <iostream>
#include <fstream>
using namespace std;

/*
All users in the file
soad91, Soad Saber, soad91@gmail.com
olaa100, Ola Kamel, olaa100@yahoo.com
emadsalem1, Emad Salem, emadsalem1@gmail.com
abdoamr, Abdelkarim Amr, abdoamr@gmail.com
fatoom, Fatma Omar, fatoom@yahoo.com
mony34, Mona Hamed, mony34@gmail.com
emy2000, Iman Kareem, emy2000@gmail.com
talaat3000, Talaat Mahmoud, talaat3000@gmail.com
samir22, Samir Abdelshakoor, samir22@gmail.com
*/

//Treap class
template<class T, class V>
class Treap {
public:
    class Node {
    public:
        Node* left, * right;
        T key;
        V value;
        int priority;
        Node(T k, V v)
        {
            key = k;
            value = v;
            priority = rand() % 100;
            left = right = NULL;
        }
         friend void displayInOrder(Treap<T, V>::Node* root) {
            if (root) {
                displayInOrder(root->left);
                cout << root->value << endl;
                displayInOrder(root->right);
            }
        }
         friend ostream& operator<<(ostream& out, Treap<T, V>::Node* root) {
            displayInOrder(root);
            return out;
        }
    };
    Treap()
    {
        root = NULL;
    }
    void insert(T k, V v)
    {
        root = insert(root, k, v);
    }
    void remove_(T k)
    {
        root = remove_(root, k);
    }

    V find(T k)
    {
        return find(root, k);
    }
    friend ostream& operator<<(ostream& o, Treap<T, V> tree)
    {
        o << tree.root;
        return o;
    }


private:
    Node* root;

    Node* leftRotation(Node* L)
    {
        Node* a = L->right, * T2 = a->left;
        a->left = L;
        L->right = T2;

        return a;
    }
    Node* rightRotation(Node* R)
    {
        Node* b = R->left, * T2 = b->right;
        b->right = R;
        R->left = T2;
        return b;
    }


    Node* insert(Node* r, T k, V v) {
        if (r == NULL) {
            Node* temp = new Node(k, v);
            return temp;
        }

        if (k <= r->key)
        {
            r->left = insert(r->left, k, v);

            if (r->left->priority > r->priority)
                r = rightRotation(r);
        }
        else
        {
            r->right = insert(r->right, k, v);

            if (r->right->priority > r->priority)
                r = leftRotation(r);
        }

        return r;
    }
    Node* remove_(Node* r, T k)
    {
        if (r == NULL)
            return r;
        if (k < r->key)
            r->left = remove_(r->left, k);
        else if (k > r->key)
            r->right = remove_(r->right, k);

        else if (r->left == NULL)
        {
            Node* temp = r->right;
            delete(r);
            r = temp;
        }
        else if (r->right == NULL)
        {
            Node* temp = r->left;
            delete(r);
            r = temp;
        }
        else if (r->left->priority < r->right->priority)
        {
            r = leftRotation(r);
            r->left = remove_(r->left, k);
        }
        else
        {
            r = rightRotation(r);
            r->right = remove_(r->right, k);
        }
        return r;
    }
    V find(Node* r, T k) {
        /*if (r == NULL)
            return 0;
            */
        if (r->key == k)
            return r->value;
        if (r->key < k)
            return find(r->right, k);
        return find(r->left, k);
    }
};


//User class
class User
{
    string username,name,email;
    Treap<string,User> tree;


public:
    User()
    {
       username = " ";
        name = " " ;
        email = " " ;
    }

     User(string user,string n,string E)
    {
        username = user;
        name = n;
        email = E;
    }

    void setUsername(string user)
    {
        username = user;
    }

    void setName(string n)
    {
        name = n;
    }

    void setEmail(string E)
    {
        email = E;
    }

    string getUsername()
    {
        return username;
    }

    string getName()
    {
        return name;
    }

    string getEmail()
    {
        return email;
    }
    void setTreap(Treap<string,User> t)
    {
        tree = t;
    }

    Treap<string,User> getTreap()
    {
        return tree;
    }


    friend ostream& operator<<(ostream& os,User& u)
    {
    os<<"Name:"<<u.getName()<<endl<<"Username:"<<u.getUsername()<<endl<<"Email:"<<u.getEmail()<<endl;
    return os;
    }

    bool operator ==(const User& user)
    {
        bool flag;
        if(username == user.username)
        {
            flag=true;
        }
        else
        {
            flag=false;
        }

        return flag;
    }



};

//Linked List class
template<class T>
class Linked_List
{

    struct Node
    {
        T value;
        Node* next;

    };

    Node* Head;
    Node* Tail;
    int len;
public:

    Linked_List()
    {
        Head = Tail = nullptr;
        len = 0;

    }

    bool IsEmpty()
    {
        if (len == 0)
        {
            return true;
        }
        return false;
    }


///////////////////////////////////
    void Insert(T elme)
    {
        Node* newNode = new Node;
        newNode->value = elme;
        if (len == 0)
        {
            Head = Tail = newNode;
            newNode->next = nullptr;
        }
        else
        {
            Tail->next=newNode;
            newNode->next = nullptr;
            Tail = newNode;
        }
        len++;
    }

//////////////////////////

    void InsertByPos(int position, T elme)
    {
        if (position<0 || position>len)
        {
            cout << "incorrect position" << endl;
        }
        else
        {
            Node* newNode = new Node;
            newNode->value = elme;
            if (position == 0)
            {
                newNode->next = Head;
                Head = newNode;
            }
            else if (position == len)
            {
                Insert(elme);
            }
            else
            {
                Node* cur = Head;
                for (int i = 1; i < position; i++)
                {
                    cur = cur->next;
                }
                newNode->next = cur->next;
                cur->next = newNode;
                len++;
            }

        }
    }

/////////////////////////////////
    void display()
    {
        Node* cur = Head;
        while (cur != nullptr)
        {
            cout << cur->value << " "<<endl;
            cur = cur->next;

        }

    }
/////////////////////////////
    void remove(T item)
    {
        if (IsEmpty())
        {
            cout << "Can not remove from empty list\n";
            return;
        }

        Node* cur, *trailCur;
        if (Head->value == item)//delete the first element, special case
        {
            cur = Head;

            Head = Head->next;
            delete cur;
            len--;
            if (len == 0)
                Tail = NULL;
        }
        else
        {
            cur = Head->next;
            trailCur = Head;
            while (cur != NULL)
            {
                if (cur->value == item)
                    break;
                trailCur = cur;
                cur = cur->next;
            }

            if (cur == NULL)
                cout << "The item is not there\n";
            else
            {
                trailCur->next = cur->next;
                if (Tail == cur) //delete the last item
                    Tail = trailCur;
                delete cur;
                len--;
            }
        }
    }

///////////////////////////

    bool Search(T item)
    {
        Node *cur = Head;
        int l = 0;
        while (cur != nullptr)
        {
            if (cur->value == item)
                return true;
            cur = cur->next;
            l++;
        }

        return false;
    }

    User SearchByUsername(string userN)
    {
        Node *cur = Head;
        int l = 0;
        while (cur != nullptr)
        {
            if (cur->value.getUsername() == userN)
                return cur->value;
            cur = cur->next;

        }
        User u("NotExist","NotExist","NotExist");
        return u;

    }

    void updateTreap(string username, Treap <string,User> treap )
    {
        Node *cur = Head;
        int l = 0;
        while (cur != nullptr)
        {
            if (cur->value.getUsername() == username)
            {
                cur->value.setTreap(treap);
                return ;
            }
            cur = cur->next;
        }
    }

    bool checkUser(string userN)
    {
        Node *cur = Head;
        int l = 0;
        while (cur != nullptr)
        {
            if (cur->value.getUsername() == userN)
                return true;
            cur = cur->next;

        }
        return false;

    }


    ~Linked_List()
    {

        Node *cur;
        while (Head != nullptr)
        {
            cur = Head;
            Head = Head->next;
            delete cur;
        }
        Tail = nullptr;
        len = 0;
    }


};

//Function Display features of logged in user
void loggedinDisplay()
{
    cout<<"1-List all friends"<<endl;
    cout<<"2-Search by username"<<endl;
    cout<<"3-Add friend"<<endl;
    cout<<"4-Remove friend"<<endl;
    cout<<"5-People you may know"<<endl;
    cout<<"6-logout"<<endl;
}






int main()
{

    Linked_List<User> List;

    //Data of users
   string username,name,email;
    ifstream users;
    users.open("all-users.in");


    while(!users.eof())
    {
        getline(users, username, ',');
        getline(users, name, ',');
        getline(users, email, '\n');
        User u(username, name, email);
        List.Insert(u);
    }
    users.close();


    //Data of users relations
    ifstream usersRelations;
    usersRelations.open("all-users-relations.in");

    string firstUsername, secondUsername ;
    while(!usersRelations.eof())
    {
        getline(usersRelations, firstUsername, ',');
        getline(usersRelations,secondUsername, '\n');
        secondUsername.erase (0,1);
        User firstUser  = List.SearchByUsername(firstUsername);
        User secondUser = List.SearchByUsername(secondUsername);

        Treap <string,User> firstUserTreap = firstUser.getTreap();
        firstUserTreap.insert(firstUser.getUsername(),secondUser);

        Treap <string,User> secondUserTreap = secondUser.getTreap();
        secondUserTreap.insert(secondUser.getUsername(),firstUser);

        List.updateTreap(firstUsername,firstUserTreap);
        List.updateTreap(secondUsername,secondUserTreap);
    }
    usersRelations.close();




    bool flag=true;
    while(flag)
    {

    cout<<"1-Login"<<endl<<"2-Exit"<<endl;
    int choice1;
    cin>>choice1;
    if(choice1==1)
    {
        cout<<"Enter Username:"<<endl;
        string userin;
        cin>>userin;
        if(List.checkUser(userin))
        {
            User loggedinUser = List.SearchByUsername(userin);
            bool flag1=true;
            while(flag1)
            {
                loggedinDisplay();
                int choice2;
                cin>>choice2;

                //List all friends
                if(choice2==1)
                {
                    cout<<"All Your Friends "<<endl<<"------------"<<endl;
                    cout<<loggedinUser.getTreap();
                }

                //Search by username
                else if(choice2==2)
                {
                    cout<<"Enter username: "<<endl;
                    string searchUser;
                    cin>>searchUser;
                    User searchedUser = List.SearchByUsername(searchUser);
                    if(List.checkUser(searchUser))
                        cout<<searchedUser<<endl;
                    else
                        cout<<"username not exist"<<endl;
                }

                //Add Friend
                else if (choice2==3)
                {
                    cout<<"Enter his username:"<<endl;
                    string addUser;
                    cin>>addUser;
                    User addedUser = List.SearchByUsername(addUser);
                    Treap <string,User> t = loggedinUser.getTreap();
                    t.insert(loggedinUser.getUsername(), addedUser);
                    loggedinUser.setTreap(t);
                    List.updateTreap(loggedinUser.getUsername(), t);

                    Treap<string,User> t2 = addedUser.getTreap();
                    t2.insert(addUser, loggedinUser);
                    addedUser.setTreap(t2);
                    List.updateTreap(addUser,t2);
                }

                //Remove a Friend
                else if (choice2==4)
                {
                    Treap<string, User>t = loggedinUser.getTreap();
                    cout<<"Enter his username: "<<endl;
                    string remUser;
                    cin>>remUser;


                    User removedUser = List.SearchByUsername(remUser);

                    t.remove_(loggedinUser.getUsername());
                    loggedinUser.setTreap(t);
                    List.updateTreap(loggedinUser.getUsername(), t);


                    Treap <string, User> t2 =removedUser.getTreap();
                    t2.remove_(removedUser.getUsername());
                    removedUser.setTreap(t2);
                    List.updateTreap(removedUser.getUsername(), t2);


                    cout<<"Friend List "<<endl<<"------"<<endl;
                    cout<<loggedinUser.getTreap()<<endl;


                }


                //People you may know
                else if (choice2==5)
                {

                    Treap<string, User> t = loggedinUser.getTreap();
                    User f = t.find(loggedinUser.getUsername());

                    users.open("all-users.in");


                    while(!users.eof())
                    {
                        getline(users, username, ',');
                        getline(users, name, ',');
                        getline(users, email, '\n');
                        User u (username,name,email);

                        if(username!=loggedinUser.getUsername() && username!=f.getUsername() )
                            cout<<u<<endl;
                    }
                        users.close();
                }


                //Logout
                else if (choice2==6)
                {
                    break;
                }


            }
        }
        else
        {
            cout<<"username not exist"<<endl;
        }
    }
    else
    {
        exit(1);
    }

    }


}
