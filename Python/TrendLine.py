import sqlite3
import sys
import matplotlib.pyplot as plt
import pandas as pd


def read_from_database(employee_id, start_date, ending_date, db_path):
    connection = sqlite3.connect(db_path)
    cursor = connection.cursor()
    params = (employee_id, start_date, ending_date)
    cursor.execute("SELECT data, type, weight FROM feedback WHERE id = ? AND data BETWEEN ? AND ? ORDER BY data", params)
    feedback = cursor.fetchall()
    connection.close()

    return feedback


employee_id = int(sys.argv[1])
start_date = sys.argv[2]
ending_date = sys.argv[3]
db_path = sys.argv[4]


data = read_from_database(employee_id, start_date, ending_date, db_path)
df = pd.DataFrame(data, columns=['date', 'type', 'weight'])

df['data'] = pd.to_datetime(df['date'])
df_aggregated = df.groupby(['data', 'type'])['weight'].sum().reset_index()

for op_type in df_aggregated['type'].unique():
    data_by_type = df_aggregated[df_aggregated['type'] == op_type]
    plt.plot(data_by_type['data'], data_by_type['weight'], label=op_type)

plt.xlabel('Date')
plt.ylabel('Weight sum')
plt.title('Trend line by opinion type')
plt.show()
