import boto3

s3 = boto3.resource('s3')

# retrieve HTML content from S3
obj = s3.Object('pipline-selenium-tomagrade-report','surefire-reports/emailable-report.html')
html_content = obj.get()['Body'].read().decode('utf-8')

client = boto3.client('ses')

response = client.send_email(
    Destination={
        'ToAddresses': [
            'netanel.malka@tomax.io',
            'ori.naaman@tomax.io',

        ],  
    },
    Message={
        'Body': {
            'Html': {
                'Data': html_content,
            },
        },
        'Subject': {
            'Data': 'Test results',
        },
    },
    Source='automation.pipline.report@gmail.com',
)

print(response)
