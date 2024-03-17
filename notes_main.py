from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
import json
notes = {}
def loadToFile():
    with open('notes.json', 'w', encoding='utf-8') as file:
        json.dump(notes, file, indent=4)
def loadNotes():
    global notes
    with open('notes.json', encoding='utf-8') as file:
        notes = json.load(file)
    noteList.clear()
    noteList.addItems(list(notes.keys()))

def showNote():
    title = noteList.selectedItems()[0].text()
    noteField.setText(notes[title]['txt'])
    tagList.clear()
    tagList.addItems(notes[title]['tags'])

def saveNote():
    try:
        title = noteList.selectedItems()[0].text()
        text = noteField.toPlainText()
        notes[title]["txt"] = text
    except IndexError:
        err = QMessageBox()
        err.setWindowTitle('Ошибка!')
        err.setStyleSheet('''
        background-color: #ff8000;
        color: 'white';
        font-size: 20px
        ''')
        err.setText("Похоже, вы не выбрали заметку из списка!!!!\nаъ")
        err.show()
        err.exec()
    loadToFile()

def addNote():
    text = noteField.toPlainText()
    title = QInputDialog.getText(win, "создание заметки", "Введите название заметки:")[0]
    notes[title] = {
        "txt": text,
        "tags": []
    }
    loadToFile()
    loadNotes()

def deleteNote():
    try:
        title = noteList.selectedItems()[0].text()
        y_n = QInputDialog.getText(win, "Удаление заметки", "Подтвердите удаление (Y/n)")[0]
        if y_n=="y" or y_n=="Y":
            noteField.clear()
            tagList.clear()
            del notes[title]
            loadToFile()
    except IndexError:
        err = QMessageBox()
        err.setWindowTitle('Ошибка!')
        err.setStyleSheet('''
        background-color: #ff8000;
        color: 'white';
        font-size: 20px
        ''')
        err.setText("Похоже, вы не выбрали заметку из списка!!!!\nаъ")
        err.show()
        err.exec()
    loadNotes()

def search():
    tag = tagInput.text()
    if tag.strip() != '' and searchTag.text() == 'Искать заметки по тегу':
        arr = []
        for title, note in notes.items():
            if tag in note['tags']:
                arr.append(title)
        noteList.clear()
        noteList.addItems(arr)
        searchTag.setText("Сбросить поиск")
        noteLabel.setStyleSheet("color: 'red'; font-size: 15px")
        noteLabel.setText('Результаты поиска:')
    else: 
        noteList.clear()
        noteList.addItems(list(notes.keys()))
        searchTag.setText('Искать заметки по тегу')
        noteLabel.setStyleSheet("color: 'black'; font-size: 11px")
        noteLabel.setText("Список заметок")

def add_Tag():
    tag = tagInput.text()
    try:
        title = noteList.selectedItems()[0].text()
    except:
        err = QMessageBox()
        err.setWindowTitle('Ошибка!')
        err.setStyleSheet('''
        background-color: #ff8000;
        color: 'white';
        font-size: 20px
        ''')
        err.setText("Похоже, вы не выбрали заметку из списка!!!!\nсначала создайте заметку без тегов, а затем добавьте их")
        err.show()
        err.exec()
        return
    if tag not in notes[title]['tags']:
        notes[title]['tags'].append(tag)
        loadToFile()
        tagList.clear()
        tagList.addItems(notes[title]['tags'])

def delTag():
    tag = tagInput.text()
    title = noteList.selectedItems()[0].text()
    if tag in notes[title]['tags']:
        notes[title]['tags'].remove(tag)
        loadToFile()
    else:
        err = QMessageBox()
        err.setWindowTitle('Ошибка!')
        err.setStyleSheet('''
        background-color: #ff8000;
        color: 'white';
        font-size: 20px;
        ''')
        err.setText("Такого тега у заметки нет")
        err.show()
        err.exec()
    tagList.clear()
    tagList.addItems(notes[title]['tags'])
    

app = QApplication([])
win = QWidget()

win.setWindowTitle('Smart Notes')
win.resize(600, 400)


noteField = QTextEdit()
noteLabel = QLabel('Список заметок')
noteList = QListWidget()
createN = QPushButton('Создать заметку')
deleteN = QPushButton('Удалить заметку')
saveN = QPushButton('Сохранить заметку')
tagLabel = QLabel('Список тегов')
tagList = QListWidget()
tagInput = QLineEdit()
tagInput.setPlaceholderText('Введите тег ...')
addTag = QPushButton('Добавить к заметке')
detachTag = QPushButton('Открепить от заметки')
searchTag = QPushButton('Искать заметки по тегу')

mainH = QHBoxLayout()
mainL = QVBoxLayout()
mainR = QVBoxLayout()

add1 = QHBoxLayout()
add2 = QHBoxLayout()
add3 = QHBoxLayout()
add4 = QHBoxLayout()
add5 = QHBoxLayout()
add6 = QHBoxLayout()
add7 = QHBoxLayout()
add8 = QHBoxLayout()
add9 = QHBoxLayout()

add1.addWidget(noteLabel)
add2.addWidget(noteList)
add3.addWidget(createN)
add3.addWidget(deleteN)
add4.addWidget(saveN)
add5.addWidget(tagLabel)
add6.addWidget(tagList)
add7.addWidget(tagInput)
add8.addWidget(addTag)
add8.addWidget(detachTag)
add9.addWidget(searchTag)

mainL.addWidget(noteField)

mainR.addLayout(add1)
mainR.addLayout(add2)
mainR.addLayout(add3)
mainR.addLayout(add4)
mainR.addLayout(add5)
mainR.addLayout(add6)
mainR.addLayout(add7)
mainR.addLayout(add8)
mainR.addLayout(add9)

mainH.addLayout(mainL, stretch=6)
mainH.addLayout(mainR, stretch=4)

loadNotes()
noteList.itemClicked.connect(showNote)
saveN.clicked.connect(saveNote)
createN.clicked.connect(addNote)
deleteN.clicked.connect(deleteNote)
searchTag.clicked.connect(search)
addTag.clicked.connect(add_Tag)
detachTag.clicked.connect(delTag)

win.setLayout(mainH)

win.show()
app.exec()