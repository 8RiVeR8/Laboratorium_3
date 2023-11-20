import sqlite3
import sys
import matplotlib.pyplot as plt
import pandas as pd


def read_from_database(person_id, date_beginning, date_end, db_way):
    connection = sqlite3.connect(db_way)
    cursor = connection.cursor()
    params = (person_id, date_beginning, date_end)
    cursor.execute("SELECT data, type, weight FROM feedback WHERE id = ? AND data BETWEEN ? AND ? ORDER BY data", params)
    report = cursor.fetchall()
    connection.close()

    return report


person_id = int(sys.argv[1])
date_beginning = sys.argv[2]
date_end = sys.argv[3]
db_way = sys.argv[4]


data = read_from_database(person_id, date_beginning, date_end, db_way)
df = pd.DataFrame(data, columns=['date', 'type', 'weight'])

df['data'] = pd.to_datetime(df['date'])
df_aggregated = df.groupby(['date', 'type'])['weight'].mean().reset_index()

for op_type in df_aggregated['type'].unique():
    data_by_type = df_aggregated[df_aggregated['type'] == op_type]
    plt.plot(data_by_type['date'], data_by_type['weight'], label=op_type)

plt.xlabel('Date')
plt.ylabel('Weight average')
plt.title('Trend line by opinion type')
plt.legend()
plt.grid(True)
plt.show()
